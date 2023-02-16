package sql;

import cn.hutool.core.util.StrUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author zhoud
 */
public class SqlCreator {

  static String menu_info_template =
      "INSERT INTO `base_common_customer`.`menu_info`(`menu_id`, `parent_menu_id`, `data_type`, `menu_name`, `req_method`, `req_url`, `menu_desc`, `order_num`, `ope_type`, `public_flg`, `res_path`, `plat_flg`, `icon`, `status`, `sysres_flg`, `remark`, `created_by`, `created_time`, `updated_by`, `updated_time`, `front_url`, `redirect_url`, `project_id`) "
          + "VALUES ('{}', '{}', NULL, '{}', 'POST', '{}', NULL, 0, NULL, 1, '/', NULL, 2, 1, NULL, NULL, '724924487694286848', now(), '724955270253379584', now(), NULL, NULL, 'dguider');\n";

  static String access_auth_template =
      "INSERT INTO `base_common_customer`.`access_auth`(`id`, `access_id`, `menu_id`, `created_by`, `created_time`) "
          + "VALUES ('{}', '1572406073459167232', '{}', '1572397563776479234', now());\n";

  static String view_template =
            "INSERT INTO base_servicecloud.auth_view(id, name, parent_id, level, type, del_flag, created_by, created_time, updated_by, updated_time) "
                    + "VALUES ('{}', '{}', '{}', '{}', '{}', 0, 1, now(), 1, now());";

    static String view_menu_template = "INSERT INTO `base_servicecloud`.`view_menu`(`id`, `menu_id`, `view_id`, `del_flag`, `created_time`, `created_by`, `updated_time`, `updated_by`) VALUES ('{}', '{}', '{}', 0, now(), '1', now(), '1'); ";

  /**
   * 参数按顺序分别为：菜单id，父菜单id，菜单名，请求方法，请求路径，开放标识
   *
   * */
  static String menu_info_template_4fsm =
      "INSERT INTO `base_common_customer`.`menu_info`(`menu_id`, `parent_menu_id`, `data_type`, `menu_name`, `req_method`, `req_url`, `menu_desc`, `order_num`, `ope_type`, `public_flg`, `res_path`, `plat_flg`, `icon`, `status`, `sysres_flg`, `remark`, `created_by`, `created_time`, `updated_by`, `updated_time`, `front_url`, `redirect_url`, `project_id`) "
          + "VALUES ('{}', '{}', NULL, '{}', '{}', '{}', NULL, 0, NULL, '{}', '/', NULL, 2, 1, NULL, NULL, '1572397563776479234', now(), '1572397563776479234', now(), NULL, NULL, 'fsmservicecloud');\n";

  public static void main(String[] args) throws Exception {
      //读文件
      File file = new File("C:\\Users\\zhoud\\Documents\\workspace\\云巡检\\FSM\\权限视图统计sql.xlsx");
        InputStream in = new FileInputStream(file);
        XSSFWorkbook sheets = new XSSFWorkbook(in);
      //获取第一个sheet
      XSSFSheet sheet = sheets.getSheetAt(0);

        //------------------------菜单--------------------------------
//        // 循环获取每一行数据
//        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
//            XSSFRow row = sheet.getRow(i);
//            // 读取每一格内容
//            //menue_id
//            String menuId = String.valueOf(IdGen.nextId());
//            //auth_id
//            String authId = String.valueOf(IdGen.nextId());
//            //parent_menu_id
//            XSSFCell arg1 = row.getCell(0); arg1.setCellType(CellType.STRING);
//            //menu_name
//            XSSFCell arg2 = row.getCell(1); arg2.setCellType(CellType.STRING);
//            //req_url
//            XSSFCell arg3 = row.getCell(2); arg3.setCellType(CellType.STRING);
//            //请求方式
//            XSSFCell arg4 = row.getCell(3); arg4.setCellType(CellType.STRING);
//            //开发标识
//            XSSFCell arg5 = row.getCell(4); arg5.setCellType(CellType.STRING);
//            String menuSql = StrUtil.format(menu_info_template_4fsm, menuId, arg1.getStringCellValue(), arg2.getStringCellValue(), arg4.getStringCellValue(), arg3.getStringCellValue()
//            , arg5.getStringCellValue());
//            String authSql = StrUtil.format(access_auth_template, authId, menuId);
//            System.out.println(menuSql + authSql);
//        }


        //-----------------------fsm视图----------------------------
        // 循环获取每一行数据
//        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
//            XSSFRow row = sheet.getRow(i);
//            // 读取每一格内容
//            //id
//            XSSFCell id = row.getCell(0); id.setCellType(CellType.STRING);
//            //name
//            XSSFCell name = row.getCell(1); name.setCellType(CellType.STRING);
//            //parent_id
//            XSSFCell parent_id = row.getCell(2); parent_id.setCellType(CellType.STRING);
//            XSSFCell level = row.getCell(3); level.setCellType(CellType.NUMERIC);
//            XSSFCell type = row.getCell(4); type.setCellType(CellType.NUMERIC);
//            String viewSql = StrUtil.format(view_template, id, name.getStringCellValue(), parent_id.getStringCellValue(), level.getNumericCellValue(), type.getNumericCellValue());
//            System.out.println(viewSql);
//        }

        //-------------------------view_menu-------------------------
      for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
          XSSFRow row = sheet.getRow(i);
          // 读取每一格内容
          //view id
          XSSFCell viewId = row.getCell(0); viewId.setCellType(CellType.STRING);
          //menu id
          XSSFCell menuId = row.getCell(2); menuId.setCellType(CellType.STRING);
          String viewSql = StrUtil.format(view_menu_template, String.valueOf(IdGen.nextId()), menuId.getStringCellValue(), viewId.getStringCellValue());
          System.out.println(viewSql);
      }

    }
}
