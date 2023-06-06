package rs.raf.rafnews.configuration;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import rs.raf.rafnews.entity.Category;
import rs.raf.rafnews.entity.News;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.factory.Factory;
import rs.raf.rafnews.factory.impl.CategoryFactory;
import rs.raf.rafnews.factory.impl.NewsFactory;
import rs.raf.rafnews.factory.impl.UserFactory;
import rs.raf.rafnews.repository.CategoryRepository;
import rs.raf.rafnews.repository.NewsRepository;
import rs.raf.rafnews.repository.UserRepository;
import rs.raf.rafnews.repository.impl.CategoryRepositoryImpl;
import rs.raf.rafnews.repository.impl.NewsRepositoryImpl;
import rs.raf.rafnews.repository.impl.UserRepositoryImpl;
import rs.raf.rafnews.service.CategoryService;
import rs.raf.rafnews.service.UserService;
import rs.raf.rafnews.service.impl.CategoryServiceImpl;
import rs.raf.rafnews.service.impl.UserServiceImpl;

import javax.inject.Singleton;

public class MyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        // repo
        bind(UserRepositoryImpl.class).to(UserRepository.class).in(Singleton.class);
        bind(CategoryRepositoryImpl.class).to(CategoryRepository.class).in(Singleton.class);
        bind(NewsRepositoryImpl.class).to(NewsRepository.class).in(Singleton.class);
        // service
        bind(UserServiceImpl.class).to(UserService.class).in(Singleton.class);
        bind(CategoryServiceImpl.class).to(CategoryService.class).in(Singleton.class);
        // factory
        bind(UserFactory.class).to(new TypeLiteral<Factory<User>>() {}).in(Singleton.class);
        bind(CategoryFactory.class).to(new TypeLiteral<Factory<Category>>() {}).in(Singleton.class);
        bind(NewsFactory.class).to(new TypeLiteral<Factory<News>>() {}).in(Singleton.class);
    }
}
