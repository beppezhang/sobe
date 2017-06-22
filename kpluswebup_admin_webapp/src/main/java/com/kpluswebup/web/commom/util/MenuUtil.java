package com.kpluswebup.web.commom.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.kpluswebup.web.vo.RoleFunctionVO;
import com.kpuswebup.comom.util.CachedClient;
import com.kpuswebup.comom.util.StringUtil;

public class MenuUtil {

    private static Logger log = Logger.getLogger(MenuUtil.class);

    @Autowired
    private CachedClient  cachedClient;

    /**
     * 用于判定某个URI是否有权限
     * 
     * @date 2014年12月16日
     * @author zhuhp
     * @param uri
     * @param menuAllList
     * @return
     * @since JDK 1.6
     * @Description
     */
    public boolean isExistMenu(String uri, List<RoleFunctionVO> menuAllList) {

        if (StringUtil.isEmpty(uri) || menuAllList == null) {
            log.error("menuAllList or  uri  is  null");
            return false;
        }
        log.info("uri=" + uri);
        for (RoleFunctionVO roleFunctionVO : menuAllList) {

            if (roleFunctionVO.getFunctionURL() != null) {
                uri = uri.split("\\.")[0].trim();
                String puri = roleFunctionVO.getFunctionURL().split("\\.")[0].trim();
                if (uri.equals(puri)) {
                    return true;
                }
            }
        }

        return false;

    }
}
