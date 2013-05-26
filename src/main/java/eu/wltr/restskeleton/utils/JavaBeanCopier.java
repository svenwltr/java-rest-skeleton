package eu.wltr.restskeleton.utils;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



/**
 * Used to clone java beans. Source:
 * http://www.mail-archive.com/commons-dev@jakarta.apache.org/msg43687.html
 */
public class JavaBeanCopier
{
	/**
	 * Returns a deeply cloned java bean.
	 * 
	 * @param fromBean
	 *            java bean to be cloned.
	 * @return a new java bean cloned from fromBean.
	 */
	public static <T> T copy(T fromBean)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		XMLEncoder out = new XMLEncoder(bos);
		out.writeObject(fromBean);
		out.close();
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		XMLDecoder in = new XMLDecoder(bis);
		Object toBean = in.readObject();
		in.close();

		@SuppressWarnings("unchecked")
		T result = (T) toBean;
		return result;
	}
}
