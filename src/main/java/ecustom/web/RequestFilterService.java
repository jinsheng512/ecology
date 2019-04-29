package ecustom.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by William on 2017-7-26.
 */
public interface RequestFilterService {

	RequestFilterResult process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
