package sql;

/**生成id
 * @author zhoud
 */
public class IdCreator {

    public static void main(String[] args) throws Exception {

        for(int i = 0; i<4; i++) {
      System.out.println(IdGen.nextId());
        }
    }


}
