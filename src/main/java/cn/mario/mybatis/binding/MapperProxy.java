package cn.mario.mybatis.binding;


import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description: 映射器代理类
 * @author: mario
 * @date: 2025/6/24
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final Long serialVersionUID = -1L;

    private Map<String, String> sqlSession;
    private final Class<T> mapperInterface;


    public MapperProxy(Class<T> mapperInterface, Map<String, String> sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "你被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }

}
