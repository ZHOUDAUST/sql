package sql;

import java.security.SecureRandom;

/**
 * 雪花算法
 * @author Noxe
 */
public class SnowFlakeIdWorker {

    /** 最小值 */
    private static final Long MIN = 0L;
    /** 最大值 */
    private static final Integer MAX = 31;
    /** 随机 */
    private static final SecureRandom RANDOM = new SecureRandom();
    /**  */
    private static final long WORKER_ID_BITS = 5L;
    /**  */
    private static final long DATACENTER_ID_BITS = 5L;
    /**  */
    private long lastTimestamp = -1L;

    /** 工作id */
    private final Long workerId;
    /** 数据id */
    private final Long datacenterId;
    /** 序列 */
    private Long sequence;

    /**
     * 无参构造
     */
    public SnowFlakeIdWorker() {
        this.workerId = MIN + RANDOM.nextInt(MAX);
        this.datacenterId = MIN + RANDOM.nextInt(MAX);
        this.sequence = 0L;
    }

    /**
     * 有参构造
     * @param workerId 工作id
     * @param datacenterId 数据id
     * @param sequence 序列
     */
    public SnowFlakeIdWorker(long workerId, long datacenterId, long sequence) {
        // sanity check for workerId
        long maxWorkerId = ~(-1L << WORKER_ID_BITS);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        long maxDatacenterId = ~(-1L << DATACENTER_ID_BITS);
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    /**
     * get
     * @return workerId
     */
    public long getWorkerId() {
        return workerId;
    }

    /**
     * get
     * @return datacenterId
     */
    public long getDatacenterId() {
        return datacenterId;
    }

    /**
     * get
     * @return time
     */
    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 生成
     * @return id
     */
    public synchronized long generate() throws Exception {
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }

        long sequenceBits = 12L;
        if (lastTimestamp == timestamp) {
            long sequenceMask = ~(-1L << sequenceBits);
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0L) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        long twepoch = 1288834974657L;
        long datacenterIdShift = sequenceBits + WORKER_ID_BITS;
        long timestampLeftShift = sequenceBits + WORKER_ID_BITS + DATACENTER_ID_BITS;
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << sequenceBits)
                | sequence;
    }

    /**
     * 获取下个时间
     * @param lastTimestamp 上个时间
     * @return 下个时间
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取时间
     * @return 时间
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
