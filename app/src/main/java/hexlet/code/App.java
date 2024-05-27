package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements  Callable<Integer> {

    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;

    @Option(names = {"-h", "--help"},
            description = "Show this help message and exit.", usageHelp = true)
    boolean help;

    @Option(names = {"-V", "--version"},
            description = "Print version information and exit.")
    boolean version;

    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    String formatName;


    @Parameters(paramLabel = "filepath1",
            index = "0",
            description = "path to first label")
    String filePath1;

    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second label")
    String filePath2;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filePath1, filePath2, formatName));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_CODE;
        }
        return SUCCESS_CODE;

    }

}


