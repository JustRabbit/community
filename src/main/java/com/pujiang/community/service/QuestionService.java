package com.pujiang.community.service;


import com.pujiang.community.dto.QuestionDTO;
import com.pujiang.community.mapper.QuestionMapper;
import com.pujiang.community.mapper.UserMapper;
import com.pujiang.community.model.Question;
import com.pujiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  业务逻辑层
 */
@Service
public class QuestionService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private QuestionMapper  questionMapper;


    public List<QuestionDTO> list() {
        List<Question> questions=questionMapper.list();
        List<QuestionDTO> questionDTOS=new ArrayList<>();
        for (Question question:questions){
            User user=userMapper.findById(question.getCreator());
            System.out.println(user);
            QuestionDTO questionDTO=new QuestionDTO();
            //类属性拷贝
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}
