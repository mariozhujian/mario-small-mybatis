package cn.mario.mybatis.type;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @description: 类型别名注册机
 * @author: mario
 * @date: 2025/6/30
 */
public class TypeAliasRegistry {

    private final Map<String, Class<?>> TYPE_ALIASES = new HashMap<>();

    public TypeAliasRegistry() {
        // 构造函数里注册系统内置的类型别名
        registerAlias("string", String.class);

        // 基本类型的别名
        registerAlias("byte", Byte.class);
        registerAlias("long", Long.class);
        registerAlias("short", Short.class);
        registerAlias("int", Integer.class);
        registerAlias("integer", Integer.class);
        registerAlias("double", Double.class);
        registerAlias("float", Float.class);
        registerAlias("boolean", Boolean.class);
    }

    public void registerAlias(String alias, Class<?> type) {
        alias = alias.toLowerCase(Locale.ENGLISH);
        this.TYPE_ALIASES.put(alias, type);
    }

    public Class<?> resolveAlias(String alias) {
        alias = alias.toLowerCase(Locale.ENGLISH);
        return TYPE_ALIASES.get(alias);
    }

}
