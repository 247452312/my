package indi.uhyils.asm;

import org.objectweb.asm.*;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月20日 10时18分
 */
public class TomcatURLStreamHandlerFactoryDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", null, "java/lang/Object", new String[]{"java/net/URLStreamHandlerFactory"});

        cw.visitSource("TomcatURLStreamHandlerFactory.java", null);

        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "WAR_PROTOCOL", "Ljava/lang/String;", null, "war");
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC, "CLASSPATH_PROTOCOL", "Ljava/lang/String;", null, "classpath");
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_STATIC + ACC_VOLATILE, "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "registered", "Z", null, null);
            fv.visitEnd();
        }
        {
            fv = cw.visitField(ACC_PRIVATE + ACC_FINAL, "userFactories", "Ljava/util/List;", "Ljava/util/List<Ljava/net/URLStreamHandlerFactory;>;", null);
            fv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE, "<init>", "(Z)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/NoSuchFieldException");
            mv.visitTryCatchBlock(l0, l1, l2, "java/lang/IllegalAccessException");
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(43, l3);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(39, l4);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitTypeInsn(NEW, "java/util/concurrent/CopyOnWriteArrayList");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/concurrent/CopyOnWriteArrayList", "<init>", "()V", false);
            mv.visitFieldInsn(PUTFIELD, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "userFactories", "Ljava/util/List;");
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitLineNumber(47, l5);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ILOAD, 1);
            mv.visitFieldInsn(PUTFIELD, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "registered", "Z");
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(48, l6);
            mv.visitVarInsn(ILOAD, 1);
            Label l7 = new Label();
            mv.visitJumpInsn(IFEQ, l7);
            mv.visitLabel(l0);
            mv.visitLineNumber(50, l0);
            mv.visitLdcInsn(Type.getType("Ljava/net/URL;"));
            mv.visitLdcInsn("factory");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getDeclaredField", "(Ljava/lang/String;)Ljava/lang/reflect/Field;", false);
            mv.visitVarInsn(ASTORE, 2);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLineNumber(51, l8);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(52, l9);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", false);
            Label l10 = new Label();
            mv.visitLabel(l10);
            mv.visitLineNumber(53, l10);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Field", "setAccessible", "(Z)V", false);
            mv.visitLabel(l1);
            mv.visitLineNumber(56, l1);
            Label l11 = new Label();
            mv.visitJumpInsn(GOTO, l11);
            mv.visitLabel(l2);
            mv.visitLineNumber(54, l2);
            mv.visitFrame(Opcodes.F_FULL, 2, new Object[]{"org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", Opcodes.INTEGER}, 1, new Object[]{
                "java/lang/ReflectiveOperationException"});
            mv.visitVarInsn(ASTORE, 2);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLineNumber(55, l12);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ReflectiveOperationException", "printStackTrace", "()V", false);
            mv.visitLabel(l11);
            mv.visitLineNumber(57, l11);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "java/net/URL", "setURLStreamHandlerFactory", "(Ljava/net/URLStreamHandlerFactory;)V", false);
            mv.visitLabel(l7);
            mv.visitLineNumber(59, l7);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(RETURN);
            Label l13 = new Label();
            mv.visitLabel(l13);
            mv.visitLocalVariable("factory", "Ljava/lang/reflect/Field;", null, l8, l1, 2);
            mv.visitLocalVariable("e", "Ljava/lang/ReflectiveOperationException;", null, l12, l11, 2);
            mv.visitLocalVariable("this", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, l3, l13, 0);
            mv.visitLocalVariable("register", "Z", null, l3, l13, 1);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "getInstance", "()Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(69, l0);
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "getInstanceInternal", "(Z)Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", false);
            mv.visitInsn(POP);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(70, l1);
            mv.visitFieldInsn(GETSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PRIVATE + ACC_STATIC, "getInstanceInternal", "(Z)Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            mv.visitTryCatchBlock(l0, l1, l2, null);
            Label l3 = new Label();
            mv.visitTryCatchBlock(l2, l3, l2, null);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(75, l4);
            mv.visitFieldInsn(GETSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            Label l5 = new Label();
            mv.visitJumpInsn(IFNONNULL, l5);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(76, l6);
            mv.visitLdcInsn(Type.getType("Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;"));
            mv.visitInsn(DUP);
            mv.visitVarInsn(ASTORE, 1);
            mv.visitInsn(MONITORENTER);
            mv.visitLabel(l0);
            mv.visitLineNumber(77, l0);
            mv.visitFieldInsn(GETSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            Label l7 = new Label();
            mv.visitJumpInsn(IFNONNULL, l7);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLineNumber(78, l8);
            mv.visitTypeInsn(NEW, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory");
            mv.visitInsn(DUP);
            mv.visitVarInsn(ILOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "<init>", "(Z)V", false);
            mv.visitFieldInsn(PUTSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            mv.visitLabel(l7);
            mv.visitLineNumber(80, l7);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/lang/Object"}, 0, null);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l1);
            mv.visitJumpInsn(GOTO, l5);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{"java/lang/Throwable"});
            mv.visitVarInsn(ASTORE, 2);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitInsn(MONITOREXIT);
            mv.visitLabel(l3);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitInsn(ATHROW);
            mv.visitLabel(l5);
            mv.visitLineNumber(82, l5);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitFieldInsn(GETSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            mv.visitInsn(ARETURN);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLocalVariable("register", "Z", null, l4, l9, 0);
            mv.visitMaxs(3, 3);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "register", "()Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(95, l0);
            mv.visitInsn(ICONST_1);
            mv.visitMethodInsn(INVOKESTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "getInstanceInternal", "(Z)Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "isRegistered", "()Z", false);
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "disable", "()Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(108, l0);
            mv.visitInsn(ICONST_0);
            mv.visitMethodInsn(INVOKESTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "getInstanceInternal", "(Z)Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "isRegistered", "()Z", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFNE, l1);
            mv.visitInsn(ICONST_1);
            Label l2 = new Label();
            mv.visitJumpInsn(GOTO, l2);
            mv.visitLabel(l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitInsn(ICONST_0);
            mv.visitLabel(l2);
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[]{Opcodes.INTEGER});
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "release", "(Ljava/lang/ClassLoader;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(119, l0);
            mv.visitFieldInsn(GETSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            Label l1 = new Label();
            mv.visitJumpInsn(IFNONNULL, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(120, l2);
            mv.visitInsn(RETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(122, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitFieldInsn(GETSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            mv.visitFieldInsn(GETFIELD, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "userFactories", "Ljava/util/List;");
            mv.visitVarInsn(ASTORE, 1);
            Label l3 = new Label();
            mv.visitLabel(l3);
            mv.visitLineNumber(123, l3);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true);
            mv.visitVarInsn(ASTORE, 2);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{"java/util/List", "java/util/Iterator"}, 0, null);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
            Label l5 = new Label();
            mv.visitJumpInsn(IFEQ, l5);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
            mv.visitTypeInsn(CHECKCAST, "java/net/URLStreamHandlerFactory");
            mv.visitVarInsn(ASTORE, 3);
            Label l6 = new Label();
            mv.visitLabel(l6);
            mv.visitLineNumber(124, l6);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getClassLoader", "()Ljava/lang/ClassLoader;", false);
            mv.visitVarInsn(ASTORE, 4);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(125, l7);
            mv.visitFrame(Opcodes.F_APPEND, 2, new Object[]{"java/net/URLStreamHandlerFactory", "java/lang/ClassLoader"}, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            Label l8 = new Label();
            mv.visitJumpInsn(IFNULL, l8);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(126, l9);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
            Label l10 = new Label();
            mv.visitJumpInsn(IFEQ, l10);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLineNumber(130, l11);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "remove", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLineNumber(131, l12);
            mv.visitJumpInsn(GOTO, l8);
            mv.visitLabel(l10);
            mv.visitLineNumber(133, l10);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ClassLoader", "getParent", "()Ljava/lang/ClassLoader;", false);
            mv.visitVarInsn(ASTORE, 4);
            mv.visitJumpInsn(GOTO, l7);
            mv.visitLabel(l8);
            mv.visitLineNumber(135, l8);
            mv.visitFrame(Opcodes.F_CHOP, 2, null, 0, null);
            mv.visitJumpInsn(GOTO, l4);
            mv.visitLabel(l5);
            mv.visitLineNumber(136, l5);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitInsn(RETURN);
            Label l13 = new Label();
            mv.visitLabel(l13);
            mv.visitLocalVariable("factoryLoader", "Ljava/lang/ClassLoader;", null, l7, l8, 4);
            mv.visitLocalVariable("factory", "Ljava/net/URLStreamHandlerFactory;", null, l6, l8, 3);
            mv.visitLocalVariable("classLoader", "Ljava/lang/ClassLoader;", null, l0, l13, 0);
            mv.visitLocalVariable("factories", "Ljava/util/List;", "Ljava/util/List<Ljava/net/URLStreamHandlerFactory;>;", l3, l13, 1);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "isRegistered", "()Z", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(139, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "registered", "Z");
            mv.visitInsn(IRETURN);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLocalVariable("this", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, l0, l1, 0);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "addUserFactory", "(Ljava/net/URLStreamHandlerFactory;)V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(153, l0);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "userFactories", "Ljava/util/List;");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            mv.visitInsn(POP);
            Label l1 = new Label();
            mv.visitLabel(l1);
            mv.visitLineNumber(154, l1);
            mv.visitInsn(RETURN);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLocalVariable("this", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, l0, l2, 0);
            mv.visitLocalVariable("factory", "Ljava/net/URLStreamHandlerFactory;", null, l0, l2, 1);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_PUBLIC, "createURLStreamHandler", "(Ljava/lang/String;)Ljava/net/URLStreamHandler;", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(162, l0);
            mv.visitLdcInsn("war");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
            Label l1 = new Label();
            mv.visitJumpInsn(IFEQ, l1);
            Label l2 = new Label();
            mv.visitLabel(l2);
            mv.visitLineNumber(163, l2);
            mv.visitTypeInsn(NEW, "org/apache/catalina/webresources/war/Handler");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "org/apache/catalina/webresources/war/Handler", "<init>", "()V", false);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l1);
            mv.visitLineNumber(164, l1);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitLdcInsn("classpath");
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
            Label l3 = new Label();
            mv.visitJumpInsn(IFEQ, l3);
            Label l4 = new Label();
            mv.visitLabel(l4);
            mv.visitLineNumber(165, l4);
            mv.visitTypeInsn(NEW, "org/apache/catalina/webresources/ClasspathURLStreamHandler");
            mv.visitInsn(DUP);
            mv.visitMethodInsn(INVOKESPECIAL, "org/apache/catalina/webresources/ClasspathURLStreamHandler", "<init>", "()V", false);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l3);
            mv.visitLineNumber(169, l3);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "userFactories", "Ljava/util/List;");
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "iterator", "()Ljava/util/Iterator;", true);
            mv.visitVarInsn(ASTORE, 2);
            Label l5 = new Label();
            mv.visitLabel(l5);
            mv.visitFrame(Opcodes.F_APPEND, 1, new Object[]{"java/util/Iterator"}, 0, null);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
            Label l6 = new Label();
            mv.visitJumpInsn(IFEQ, l6);
            mv.visitVarInsn(ALOAD, 2);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
            mv.visitTypeInsn(CHECKCAST, "java/net/URLStreamHandlerFactory");
            mv.visitVarInsn(ASTORE, 3);
            Label l7 = new Label();
            mv.visitLabel(l7);
            mv.visitLineNumber(170, l7);
            mv.visitVarInsn(ALOAD, 3);
            mv.visitVarInsn(ALOAD, 1);
            Label l8 = new Label();
            mv.visitLabel(l8);
            mv.visitLineNumber(171, l8);
            mv.visitMethodInsn(INVOKEINTERFACE, "java/net/URLStreamHandlerFactory", "createURLStreamHandler", "(Ljava/lang/String;)Ljava/net/URLStreamHandler;", true);
            mv.visitVarInsn(ASTORE, 4);
            Label l9 = new Label();
            mv.visitLabel(l9);
            mv.visitLineNumber(172, l9);
            mv.visitVarInsn(ALOAD, 4);
            Label l10 = new Label();
            mv.visitJumpInsn(IFNULL, l10);
            Label l11 = new Label();
            mv.visitLabel(l11);
            mv.visitLineNumber(173, l11);
            mv.visitVarInsn(ALOAD, 4);
            mv.visitInsn(ARETURN);
            mv.visitLabel(l10);
            mv.visitLineNumber(175, l10);
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            mv.visitJumpInsn(GOTO, l5);
            mv.visitLabel(l6);
            mv.visitLineNumber(178, l6);
            mv.visitFrame(Opcodes.F_CHOP, 1, null, 0, null);
            mv.visitInsn(ACONST_NULL);
            mv.visitInsn(ARETURN);
            Label l12 = new Label();
            mv.visitLabel(l12);
            mv.visitLocalVariable("handler", "Ljava/net/URLStreamHandler;", null, l9, l10, 4);
            mv.visitLocalVariable("factory", "Ljava/net/URLStreamHandlerFactory;", null, l7, l10, 3);
            mv.visitLocalVariable("this", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;", null, l0, l12, 0);
            mv.visitLocalVariable("protocol", "Ljava/lang/String;", null, l0, l12, 1);
            mv.visitMaxs(2, 5);
            mv.visitEnd();
        }
        {
            mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
            mv.visitCode();
            Label l0 = new Label();
            mv.visitLabel(l0);
            mv.visitLineNumber(34, l0);
            mv.visitInsn(ACONST_NULL);
            mv.visitFieldInsn(PUTSTATIC, "org/apache/catalina/webresources/TomcatURLStreamHandlerFactory", "instance", "Lorg/apache/catalina/webresources/TomcatURLStreamHandlerFactory;");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 0);
            mv.visitEnd();
        }
        cw.visitEnd();

        return cw.toByteArray();
    }
}

