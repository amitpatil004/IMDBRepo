package IMDB.Config;

import java.text.MessageFormat;
import java.util.List;

import DataModels.Movies;

public class Helper {
	public  static String BuildHTML(List<Movies> allMovies) {
		String html = "<tr>\r\n" + "   <th>[{0}]</th>\r\n" + "    <th>[{1}]</th> \r\n" + "  </tr>";

		String finalHtml = null;
		for (int i = 1; i < allMovies.size(); i++) {
			MessageFormat mf = new MessageFormat(html);
			String replacedHTML = mf.format(new Object[] { allMovies.get(i).rank_Title, allMovies.get(i).rating });

			finalHtml = finalHtml + replacedHTML;
		}
		return finalHtml;
	}
}
