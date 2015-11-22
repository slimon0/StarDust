package umiker9.stardust2d.Systems.Log;

import java.io.PrintStream;

/**
 * Created by Notezway on 23.11.2015.
 */
public class Logger {

    private static Logger instance;

    private int minLevel;
    private PrintStream[] out;
    private LogLevel currLevel, defaultLevel;

    public Logger() {
        this(System.out);
    }

    public Logger(PrintStream... out) {
        this(LogLevel.INFO, out);
    }

    public Logger(LogLevel minLevel, PrintStream... out) {
        this(minLevel, minLevel, out);
    }

    public Logger(LogLevel minLevel, LogLevel defaultLevel, PrintStream... out) {
        this.minLevel = minLevel.ordinal();
        this.out = out;
        this.currLevel = minLevel;
        this.defaultLevel = defaultLevel;
    }

    public void log(Message message) {
        if(message.getLevel().ordinal() >= minLevel) {
            for (PrintStream ps : out) {
                ps.println(message.toString());
            }
        }
    }

    public void log(String text) {
        log(new Message(currLevel, text));
        currLevel = defaultLevel;
    }

    public void setLevel(LogLevel level) {
        currLevel = level;
    }

    public void setMinLevel(LogLevel level) {
        minLevel = level.ordinal();
    }

    public void setDefaultLevel(LogLevel level) {
        defaultLevel = level;
    }

    public static void logInst(Message message) {
        instance.log(message);
    }

    public static void logInst(String text) {
        instance.log(text);
    }

    public static void setLevelInst(LogLevel level) {
        instance.setLevel(level);
    }

    public static void setMinLevelInst(LogLevel level) {
        instance.setMinLevel(level);
    }

    public static void setDefaultLevelInst(LogLevel level) {
        instance.setDefaultLevel(level);
    }

    public static void createInstance(Logger logger) {
        instance = logger;
    }

    public static void createDefaultInstance() {
        instance = new Logger();
    }

    public static Logger getInstance() {
        return instance;
    }

    public static Message newInfo(String text) {
        return new Message(LogLevel.INFO, text);
    }

    public static Message newWarn(String text) {
        return new Message(LogLevel.WARNING, text);
    }

    public static Message newError(String text) {
        return new Message(LogLevel.ERROR, text);
    }

    public static Message newCritError(String text) {
        return new Message(LogLevel.CRITICAL_ERROR, text);
    }
}