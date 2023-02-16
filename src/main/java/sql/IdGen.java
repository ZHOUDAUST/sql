package sql;

/**
 * id生成器
 * @author Noxe
 */
public class IdGen {

    /** id生成器 */
    private static final SnowFlakeIdWorker SNOW_FLAKE_ID_WORKER = new SnowFlakeIdWorker();

    /**
     * 私有化构造
     */
    private IdGen() { }

    /**
     * 获取id
     * @return id
     */
    public static long nextId() throws Exception {
        return SNOW_FLAKE_ID_WORKER.generate();
    }

}
