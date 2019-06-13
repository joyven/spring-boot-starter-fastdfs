package org.springframework.fasfdfs.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * Date: 2019/1/28
 * Time: 9:10 PM
 * Description:
 *
 * @author zhoujunwen
 * @version 1.0
 */
@Getter
@Setter
public class FdfsException extends RuntimeException {
    private int code;
    private String msg;

    public FdfsException() {

    }

    public FdfsException(int code, String message) {

    }
}
