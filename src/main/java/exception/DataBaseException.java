package exception;

/**
 * このシステム内で発生した例外を示すクラス
 */
public class DataBaseException extends Exception {
	/**
	 * コンストラクタ<br>
	 * 画面に表示するメッセージ用に引数で受け取った値をセットします
	 * @param message 発生した内容を示すメッセージ
	 */
	public DataBaseException(String message) {
		super(message);
	}
}