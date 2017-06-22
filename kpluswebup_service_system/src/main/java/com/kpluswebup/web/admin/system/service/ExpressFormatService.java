package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.vo.ExpressFormatVO;

public interface ExpressFormatService {

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param name
     * @param expressID
     * @param picURL
     * @param width
     * @param height
     * @since JDK 1.6
     * @Description
     */
    public String addExpressFormat(String name, String expressID, String picURL, String width, String height,
                                 String isDefault);

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ExpressFormatVO> findALLExpressFormat();

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteExpressFormatByMainID(String mainID);

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public ExpressFormatVO findExpressFormatByMainID(String mainID);

    /**
     * @date 2014年11月22日
     * @author zhuhp
     * @param mainID
     * @param name
     * @param expressID
     * @param isDefault
     * @param picURL
     * @param width
     * @param height
     * @since JDK 1.6
     * @Description
     */
    public void editExpressFormat(String mainID, String name, String expressID, String isDefault, String picURL,
                                  String width, String height,String currentOperator);

}
