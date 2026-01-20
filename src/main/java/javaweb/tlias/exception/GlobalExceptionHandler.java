package javaweb.tlias.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javaweb.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice//全局异常处理类
public class GlobalExceptionHandler {

    /**
     * 处理所有异常
     * @param e
     * @return
     */
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("发生异常：{}",e.getMessage());
        return Result.error(e.getMessage());
    }

     /**
      * 处理自定义异常P115
      * @param e
      * @return
      */


}
