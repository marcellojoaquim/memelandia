package com.marcello.post_server.domain.converter;

import com.marcello.post_server.domain.entity.Post;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class UppercaseEventListener extends AbstractMongoEventListener<Post> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Post> event) {
        Post post = event.getSource();
        if(post.getCategoria() != null){
            post.setCategoria(post.getCategoria().toUpperCase());
        }
    }
}
