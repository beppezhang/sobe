package com.kpuswebup.comom.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.lang.ClassUtils;

public final class ClassTools
{
  public static Class getClass(String paramString) throws Exception
  {
    try
    {
      return ClassUtils.getClass(paramString);
    }
    catch (Throwable localThrowable)
    {
      throw new Exception("ClassTools.getClass异常: ClassName = %s");
    }
  }

  public static Object getInstance(Class paramClass) throws Exception
  {
    try
    {
      return paramClass.newInstance();
    }
    catch (Throwable localThrowable)
    {
      throw new Exception("ClassTools.getInstance异常: ClassName = %s");
    }
  }

  public static Throwable getCause(Throwable paramThrowable)
  {
    Object localObject = paramThrowable;
    while (true)
    {
      Throwable localThrowable = ((Throwable)localObject).getCause();
      if (localThrowable == null) {
        break;
      }
      localObject = localThrowable;
    }

    return (Throwable) localObject;
  }

  public static String getValidMessage(Throwable paramThrowable)
  {
    String str = getCause(paramThrowable).getLocalizedMessage();
    if ((str == null) || (str.trim().length() == 0)) {
      str = paramThrowable.getLocalizedMessage();
    }
    return str;
  }

  public static <T extends Throwable> T getThrowable(Throwable paramThrowable, Class<T> paramClass)
  {
    if (paramThrowable.getClass() == paramClass) {
      return (T) paramThrowable;
    }
    Object localObject = paramThrowable;
    while (true)
    {
      Throwable localThrowable = ((Throwable)localObject).getCause();
      if (localThrowable == null) {
        break;
      }
      if (localThrowable.getClass() == paramClass) {
        return (T) localThrowable;
      }
      localObject = localThrowable;
    }

    return null;
  }

  public static boolean hasAncestral(Class<?> paramClass1, Class<?> paramClass2)
  {
    Object localObject = paramClass1;
    while (true)
    {
      Class localClass = ((Class)localObject).getSuperclass();
      if (localClass == null) {
        break;
      }
      if (localClass == paramClass2) {
        return true;
      }
      localObject = localClass;
    }

    return false;
  }

  public static <T extends Serializable> T clone(T paramT) throws Exception
  {
    Serializable localSerializable = null;
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramT);
      localObjectOutputStream.close();

      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
      ObjectInputStream localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);

      localSerializable = (Serializable)localObjectInputStream.readObject();
      localObjectInputStream.close();
    }
    catch (Throwable localThrowable)
    {
      throw new Exception("CloneTools.clone异常: ClassName = %s");
    }
    return (T) localSerializable;
  }

  public static Method getGetMethod(Class paramClass, String paramString)
    throws NoSuchMethodException
  {
    return paramClass.getMethod("get" + paramString, new Class[0]);
  }

  public static String getPropertyName4Method(String paramString)
  {
    return paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
  }
}