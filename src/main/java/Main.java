import com.mjc.school.NewsManagement;
import com.mjc.school.controller.NewsController;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.mapper.Mapper;

public class Main {

    public static void main(String[] args){
        NewsManagement.init();
    }
}
