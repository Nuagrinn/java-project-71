package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

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
    String filepath1;

    @Parameters(paramLabel = "filepath2",
            index = "1",
            description = "path to second label")
    String filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

    }
}


