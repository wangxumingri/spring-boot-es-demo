//package com.wxss.springbootesdemo.config;
//
//import com.wxss.springbootesdemo.base.ResultBuilder;
//import com.wxss.springbootesdemo.base.ResultVo;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
///**
//   * 不好使
// * @Author: wxss
// * @Date: 2020/11/26
// * @Description:
// */
//@Controller
//public class CustomErrorController extends BasicErrorController {
//
//
//    public CustomErrorController() {
//        super(new DefaultErrorAttributes(),new ErrorProperties());
//    }
//
//    @RequestMapping(produces = "text/html",value = "/404")
//    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response){
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> errorAttributes = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        errorAttributes.put("key","自定义信息");
//        return new ModelAndView("error/404",errorAttributes);
//    }
//
//    @RequestMapping(value = "/404")
//    @ResponseBody
//    public ResponseEntity<ResultVo> errorJson404(HttpServletRequest request, HttpServletResponse response){
//        response.setStatus(getStatus(request).value());
//        Map<String, Object> errorAttributes = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        errorAttributes.put("key","自定义信息");
//        ResultVo<Object> fail = ResultBuilder.FAIL();
//
//        return new ResponseEntity<>(fail,getStatus(request));
//    }
//}
