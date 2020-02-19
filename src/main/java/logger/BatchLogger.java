package logger;

import java.util.ArrayList;

public class BatchLogger extends FileLogger{

    public BatchLogger(String filePath) {
        super(filePath);
    }

    public void writeLine(ArrayList<String> lineContent){
        for(int i=0;i<lineContent.size();++i){
            super.write(lineContent.get(i)+TAB);
        }
        super.nextLine();
    }

    public void writeLine(String[] lineContent){
        for(int i=0;i<lineContent.length;++i){
            super.write(lineContent[i]+TAB);
        }
        super.nextLine();
    }
}
