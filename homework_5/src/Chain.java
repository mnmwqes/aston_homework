abstract class Logger {
    protected Logger next;

    public void setNext(Logger next) {
        this.next = next;
    }

    public void log(String message, int level) {
        if (canHandle(level)) {
            writeMessage(message);
        } else if (next != null) {
            next.log(message, level);
        }
    }

    protected abstract boolean canHandle(int level);
    protected abstract void writeMessage(String message);
}

class InfoLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        return level == 1;
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("INFO: " + message);
    }
}

class ErrorLogger extends Logger {
    @Override
    protected boolean canHandle(int level) {
        return level == 2;
    }

    @Override
    protected void writeMessage(String message) {
        System.out.println("ERROR: " + message);
    }
}

public class Chain {
    public static void main(String[] args) {
        Logger info = new InfoLogger();
        Logger error = new ErrorLogger();

        info.setNext(error);

        info.log("Система запущена", 1);
        info.log("Произошла ошибка!", 2);
    }
}