package sql;

import com.cmsr.common.util.FrontSecurityUtil;

public class Encrypt {
  public static void main(String[] args) {
    //blankDecrypt
//      String cellPhone  = "18899995555";
//      String userName  = "fsm1";
//      String mail  = "test@qq.com";
//      String cellPhoneEncrypt = FrontSecurityUtil.encrypt(cellPhone);
//      System.out.println(cellPhoneEncrypt);
//      String userNameEncrypt = FrontSecurityUtil.encrypt(userName);
//      System.out.println(userNameEncrypt);
      System.out.println(FrontSecurityUtil.encrypt(""));

//    System.out.println(FrontSecurityUtil.decrypt("IPD+jPjVJbryX23pOCwAfA=="));
  }

//    public void blankDecrypt() {
//
//
//    }
//
//    public void decryptParam() {
//        String cellPhone  = "037+aCq32qgUW8z5SREj9ILfjeZNVWSHm1R9vy";
//        String cellPhoneEncrypt = FrontSecurityUtil.decrypt(cellPhone);
//        System.out.println(cellPhoneEncrypt);
//
//    }
}
