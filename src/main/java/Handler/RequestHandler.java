package Handler;

import java.io.*;

/**
 * Top level parent of the Requests
 */

public class RequestHandler {

    /**
     * Reads a String from an InputStream
     * @param is
     * @return
     * @throws IOException
     */
        public String readString(InputStream is) throws IOException{
            StringBuilder sb = new StringBuilder();
            InputStreamReader sr = new InputStreamReader(is);
            char[] buf = new char[1024];
            int len;
            while ((len = sr.read(buf)) > 0) {
                sb.append(buf, 0, len);
            }
            return sb.toString();
        }

        public void writeString(String s, OutputStream os) throws IOException {
            OutputStreamWriter osw = new OutputStreamWriter(os);
            osw.write(s);
            osw.flush();
        }
}
