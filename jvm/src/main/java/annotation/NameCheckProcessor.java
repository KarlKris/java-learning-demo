package annotation;

import other.NameChecker;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * @Description 利用插入式注解处理器 实现 驼峰命名校验
 * @Author li-yuanwen
 * @Date 2021/1/26 10:25
 */
// 代表了这个注解处理器对哪些注解感兴趣 * 表示所有注解
@SupportedAnnotationTypes("*")
// 代表支持的JDK 版本
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor {


    // 检测
    private NameChecker nameChecker;

    /**
     *  注解处理器初始化
     * @param processingEnv 代表了注解处理器框架提供的一个上下文环境，
     *        要创建新的代码，向编译器输出信息，获取其他工具类等都需要用到
     **/
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.nameChecker = new NameChecker(processingEnv);
    }

    /**
     * @param annotations 注解处理器所要处理的注解集合
     * @param roundEnv    当前轮次中的抽象语法树节点，每个节点在这里都可以表示成Element
     * @return false 每一个注解处理器在运行时都是单例的，
     * 如果不需要改变或添加抽象语法树中的内容，返回false,通知编译器这个轮次中的代码未发生变化
     **/
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }
        for (Element element : roundEnv.getRootElements()) {
            nameChecker.checkNames(element);
        }
        return false;
    }
}
