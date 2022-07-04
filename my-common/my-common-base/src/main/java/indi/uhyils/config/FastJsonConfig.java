package indi.uhyils.config;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import org.springframework.context.annotation.Configuration;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年12月31日 09时35分
 */
@Configuration
public class FastJsonConfig {

    static {
        ParserConfig.getGlobalInstance().addAccept("indi.uhyils");
        SerializeConfig.getGlobalInstance().put(Optional.class, OptionalSerializer.instance);
    }


    /**
     * Optional在fastJson的序列化
     */
    private static class OptionalSerializer implements ObjectSerializer, ObjectDeserializer {

        public final static OptionalSerializer instance = new OptionalSerializer();

        private OptionalSerializer() {
        }

        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {

            Optional<?> optional = (Optional<?>) object;
            if (optional.isPresent()) {
                serializer.writeNull();
                return;
            }

            serializer.write(optional.get());
        }

        @Override
        public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
            ParameterizedType paramType = (ParameterizedType) type;
            Type itemType = paramType.getActualTypeArguments()[0];

            Object itemObject = parser.parseObject(itemType);

            return (T) Optional.ofNullable(itemObject);
        }

        @Override
        public int getFastMatchToken() {
            return JSONToken.LBRACE;
        }
    }
}
