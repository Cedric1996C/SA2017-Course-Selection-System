package hello.Service;

import hello.Exception.StorageException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by njucong on 2017/5/31.
 */
public interface ImportService {

    //本地缓存文件
    public String store(MultipartFile file) throws StorageException;


}