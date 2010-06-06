package org.intrace.output.trace;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.intrace.output.AgentHelper;
import org.intrace.output.IInstrumentationHandler;

/**
 * Implements Standard Output Tracing
 */
public class TraceHandler implements IInstrumentationHandler
{
  private boolean entryExitTrace = true;
  private boolean branchTrace = false;
  private boolean argTrace = false;

  private static boolean stdOut = true;
  private static boolean fileOut = false;
  private static boolean netOut = false;

  private static final TraceSettings traceSettings = new TraceSettings("");

  public String getResponse(String args)
  {
    TraceSettings oldSettings = new TraceSettings(traceSettings);
    traceSettings.parseArgs(args);

    if ((oldSettings.isEntryExitTraceEnabled() != traceSettings
                                                               .isEntryExitTraceEnabled())
        || (oldSettings.isBranchTraceEnabled() != traceSettings
                                                               .isBranchTraceEnabled())
        || (oldSettings.isArgTraceEnabled() != traceSettings
                                                            .isArgTraceEnabled())
        || (oldSettings.isStdoutTraceOutputEnabled() != traceSettings
                                                                     .isStdoutTraceOutputEnabled())
        || (oldSettings.isFileTraceOutputEnabled() != traceSettings
                                                                   .isFileTraceOutputEnabled())
        || (oldSettings.isNetTraceOutputEnabled() != traceSettings
                                                                  .isNetTraceOutputEnabled()))
    {
      System.out.println("## Trace Settings Changed");
    }

    entryExitTrace = traceSettings.isEntryExitTraceEnabled();
    branchTrace = traceSettings.isBranchTraceEnabled();
    argTrace = traceSettings.isArgTraceEnabled();

    stdOut = traceSettings.isStdoutTraceOutputEnabled();
    fileOut = traceSettings.isFileTraceOutputEnabled();
    netOut = traceSettings.isNetTraceOutputEnabled();

    return null;
  }

  public Map<String, String> getSettingsMap()
  {
    return traceSettings.getSettingsMap();
  }

  @Override
  public void val(String desc, String className, String methodName, byte byteArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + byteArg);
    }
  }

  public void val(String desc, String className, String methodName,
                  byte[] byteArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(byteArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  short shortArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + shortArg);
    }
  }

  public void val(String desc, String className, String methodName,
                  short[] shortArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(shortArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName, int intArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + intArg);
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  int[] intArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(intArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName, long longArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + longArg);
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  long[] longArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(longArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  float floatArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + floatArg);
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  float[] floatArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(floatArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  double doubleArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + doubleArg);
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  double[] doubleArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(doubleArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  boolean boolArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + boolArg);
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  boolean[] boolArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(boolArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName, char charArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + charArg);
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  char[] charArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.toString(charArrayArg));
    }
  }

  @Override
  public void val(String desc, String className, String methodName,
                  Object objArg)
  {
    // Array return values pass through this arm so we must do something
    // a bit special - use Arrays.deepToString and discard the surrounding
    // [] that we add.
    if (argTrace)
    {
      String objStr = Arrays.deepToString(new Object[]
      { objArg });
      objStr = objStr.substring(1, objStr.length() - 1);
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + objStr);
    }
  }

  public void val(String desc, String className, String methodName,
                  Object[] objArrayArg)
  {
    if (argTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": " + desc
                                    + ": " + Arrays.deepToString(objArrayArg));
    }
  }

  @Override
  public void branch(String className, String methodName, int lineNo)
  {
    if (branchTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": /:"
                                    + lineNo);
    }
  }

  public void caught(String className, String methodName, int lineNo,
                     Throwable throwable)
  {
    if (branchTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName
                                    + ":CaughtException:" + lineNo + ": "
                                    + throwableToString(throwable));
    }
  }

  private String throwableToString(Throwable throwable)
  {
    StringBuilder throwToStr = new StringBuilder();
    if (throwable == null)
    {
      throwToStr.append("null");
    }
    else
    {
      StringWriter strWriter = new StringWriter();
      PrintWriter writer = new PrintWriter(strWriter);
      throwable.printStackTrace(writer);
      throwToStr.append(strWriter.toString());
    }
    return throwToStr.toString();
  }

  @Override
  public void enter(String className, String methodName, int lineNo)
  {
    if (entryExitTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": {:"
                                    + lineNo);
    }
  }

  @Override
  public void exit(String className, String methodName, int lineNo)
  {
    if (entryExitTrace)
    {
      TraceHandler.writeTraceOutput(className + ":" + methodName + ": }:"
                                    + lineNo);
    }
  }

  /**
   * Write output to zero or more of the following.
   * <ul>
   * <li>StdOut
   * <li>FileOut
   * <li>NetworkOut
   * </ul>
   * 
   * @param xiOutput
   */
  public static void writeTraceOutput(String xiOutput)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    long threadID = Thread.currentThread().getId();
    String traceString = "[" + dateFormat.format(new Date()) + "]:[" + threadID
                         + "]:" + xiOutput;
    if (stdOut)
    {
      System.out.println(traceString);
    }

    if (fileOut)
    {
      writeFileTrace(traceString);
    }

    if (netOut)
    {
      AgentHelper.writeDataOutput(traceString);
    }
  }

  public static synchronized void writeFileTrace(String traceString)
  {
    PrintWriter outputWriter;
    outputWriter = traceSettings.getFileTraceWriter();
    outputWriter.println(traceString);
    outputWriter.flush();
  }
}
