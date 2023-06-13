package rs.raf.rafnews.configuration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rs.raf.rafnews.mapper.NewsMapper;
import rs.raf.rafnews.repository.*;
import rs.raf.rafnews.repository.impl.*;
import rs.raf.rafnews.service.CategoryService;
import rs.raf.rafnews.service.CommentService;
import rs.raf.rafnews.service.NewsService;
import rs.raf.rafnews.service.UserService;
import rs.raf.rafnews.service.impl.CategoryServiceImpl;
import rs.raf.rafnews.service.impl.CommentServiceImpl;
import rs.raf.rafnews.service.impl.NewsServiceImpl;
import rs.raf.rafnews.service.impl.UserServiceImpl;

import javax.inject.Singleton;

public class MyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        // repo
        bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);
        bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
        bind(NewsRepositoryImpl.class).to(NewsRepository.class).in(Singleton.class);
        bind(TagRepositoryImpl.class).to(TagRepository.class).in(Singleton.class);
        bind(RoleRepositoryImpl.class).to(RoleRepository.class).in(Singleton.class);
        bind(CommentRepositoryImpl.class).to(CommentRepository.class).in(Singleton.class);
        // service
        bind(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
        bind(CategoryServiceImpl.class).to(CategoryService.class).in(Singleton.class);
        bind(NewsServiceImpl.class).to(NewsService.class).in(Singleton.class);
        bind(CommentServiceImpl.class).to(CommentService.class).in(Singleton.class);
        //mapper
        bind(NewsMapper.class).to(NewsMapper.class).in(Singleton.class);
    }
}
