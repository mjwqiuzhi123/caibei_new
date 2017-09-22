package com.borrow.supermarket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil
{
  public static boolean isDir(FTPClient ftp, String fileName)
  {
    try
    {
      return ftp.changeWorkingDirectory(fileName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static FTPClient getFtpClient() {
    try {
      FTPClient client = new FTPClient();
      boolean isSuccess = connect(client);
      if (isSuccess)
        return client;
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static boolean connect(FTPClient ftp) throws Exception {
    boolean result = false;

    ftp.connect(SystemProperty.getProperty("ftp.ip"), Integer.valueOf(SystemProperty.getProperty("ftp.port")).intValue());
    ftp.login(SystemProperty.getProperty("ftp.name"), SystemProperty.getProperty("ftp.password"));
    ftp.setFileType(2);
    int reply = ftp.getReplyCode();
    if (!FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      return result;
    }
    result = true;
    return result;
  }

  public static Map<String, Object> upload(File file, String relativePath) throws Exception {
    Map returnResult = new HashMap();
    try {
      FTPClient ftp = getFtpClient();
      ftpCreateDirectoryTree(ftp, relativePath);
      File file2 = new File(file.getPath());
      FileInputStream input = new FileInputStream(file2);
      System.out.println(file2.getName());
      ftp.storeFile(file2.getName(), input);
      input.close();
    } catch (Exception e) {
      returnResult.put("flag", Boolean.valueOf(false));
      returnResult.put("msg", e.getMessage());
      return returnResult;
    }
    returnResult.put("flag", Boolean.valueOf(true));
    returnResult.put("msg", relativePath);
    return returnResult;
  }

  private void close(FTPClient ftp, FileOutputStream fos)
  {
    try {
      if (fos != null) {
        fos.close();
      }
      ftp.logout();
      ftp.disconnect();
    }
    catch (IOException localIOException)
    {
    }
  }

  public static void showPicture(String pic_path, HttpServletResponse response) throws Exception
  {
    System.out.println(pic_path);
    FileInputStream is = new FileInputStream(pic_path);
    int i = is.available();
    byte[] data = new byte[i];
    is.read(data);
    is.close();
    response.setContentType("image/*");
    OutputStream toClient = response.getOutputStream();
    toClient.write(data);
    toClient.close();
  }

  private static void ftpCreateDirectoryTree(FTPClient ftp, String dirTree) throws IOException
  {
    boolean dirExists = true;

    String[] directories = dirTree.split("/");
    for (String dir : directories)
      if (!dir.isEmpty()) {
        if (dirExists) {
          dirExists = ftp.changeWorkingDirectory(dir);
        }
        if (!dirExists) {
          if (!ftp.makeDirectory(dir)) {
            throw new IOException("Unable to create remote directory '" + dir + "'.  error='" + ftp.getReplyString() + "'");
          }
          if (!ftp.changeWorkingDirectory(dir))
            throw new IOException("Unable to change into newly created remote directory '" + dir + "'.  error='" + ftp.getReplyString() + "'");
        }
      }
  }
}