import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}
	

	// ゲームクラス
	public static class Game {
		
		// プレイヤー、マップ、バトル
		private Player player;
		private Map map;
		private Battle currentBattle;
		private Scanner stdIn = new Scanner(System.in);
		
		// マップのサイズ
		public Game() {
			this.map = new Map(20, 20, this);
		}
		
		// ゲーム開始
		public void start() {
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			System.out.println("　　　　私のオリジナルＲＰＧへようこそ！　　　　");
			System.out.println("　　　　　　早速ゲームを開始します！　　　　　　");
			System.out.println("　まずはあなたのキャラクターを作成してください　");
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			player = new Player("hogename", "hogegender", "hogerace", "hogejob");
			player.input();
			mainLoop();
		}
		
		// メインループ
		public void mainLoop() {
			while (true) {
				map.display();
				System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
				System.out.println("次の行動を選んで入力してください！");
				System.out.println("1. 移動する");
				System.out.println("2. ステータスを確認する");
				System.out.println("3. ゲームを終了する");
				System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
				int choice = mukounaInput();
				
				switch (choice) {
					case 1:
						map.move(player);
						break;
					case 2:
						player.status();
						break;
					case 3:
						System.out.println("ゲームを終了します！");
						System.out.println("またプレイしてくださいね！");
						return;
					default:
						System.out.println("無効な選択です！正しい数字を入力してください！");
						break;
				}
			}
		}
		
		// 無効な入力がされたとき
		private int mukounaInput() {
			while (!stdIn.hasNextInt()) {
				System.out.println("無効な選択です！正しい数字を入力してください！");
				stdIn.next();
			}
			return stdIn.nextInt();
		}
		
		public Battle getCurrentBattle() {
			return currentBattle;
		}
		
		public void setCurrentBattle(Battle currentBattle) {
			this.currentBattle = currentBattle;
		}
	}
	
		
	// キャラクタークラス
	public static class Character {
		
		// 名前、性別、種族、職業、レベル、HP、攻撃力、防御力、経験値、経験値100でレベルアップ
		public String name;
		public String gender;
		public String race;
		public String job;
		public int level;
		public int hp;
		public int attackpower;
		public int defensepower;
		public int keikenchi;
		public static final int EXPERIENCE_TO_LEVEL_UP = 100;
		
		// コンストラクタ
		public Character(String name, String gender, String race, String job) {
			this.name = name;
			this.gender = gender;
			this.race = race;
			this.job = job;
			this.level = 1;
			this.hp = 100;
			this.attackpower = 5;
			this.defensepower = 5;
			this.keikenchi = 0;
		}
		
		// プレイヤーが情報を入力する
		public void input() {
			Scanner stdIn = new Scanner(System.in);
			
			System.out.println("名前を入力してください：");
			this.name = stdIn.nextLine();
			System.out.println("性別を入力してください：");
			this.gender = stdIn.nextLine();
			System.out.println("種族を入力してください：");
			this.race = stdIn.nextLine();
			System.out.println("職業を入力してください：");
			this.job = stdIn.nextLine();
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			System.out.println("　ありがとうございました！これがあなたのマップです！　");
			System.out.println("　★はあなたの位置です！どこかにモンスターが隠れています！　");
			System.out.println("　モンスターと戦って、経験値とレベルを上げていきましょう！　");			
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
		}
		
		// それぞれの数値がアップする
		public void levelUp() {
			
			// レベル、HP、攻撃力、防御力、経験値、経験値をレベルアップしたら引く
			this.level++;
			this.hp += 20;
			this.attackpower += 5;
			this.defensepower += 5;
			this.keikenchi -= EXPERIENCE_TO_LEVEL_UP;
			
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			System.out.println("レベルアップしました！現在のレベルは" + level + "です！");
			System.out.println("ＨＰが２０上昇しました！");
			System.out.println("攻撃力と防御力が５ずつ上昇しました！");
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
		}
		
		// レベルを返す
		public int getLevel() {
			return level;
		}
		
		// 経験値が達したらレベルアップ
		public void addExperience(int amount) {
			this.keikenchi += amount;
			System.out.println("経験値が" + amount + "上がりました！");
			System.out.println("これからもがんばりましょう！");
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			
			while (this.keikenchi >= EXPERIENCE_TO_LEVEL_UP) {
				levelUp();
			}
		}
		
		// ステータスを表示する
		public void status() {
			System.out.println("＝＝＝＝＝＝＝");
			System.out.println("ステータス");
			System.out.println("名前：" + name);
			System.out.println("性別：" + gender);
			System.out.println("種族：" + race);
			System.out.println("職業：" + job);
			System.out.println("レベル：" + level);
			System.out.println("ＨＰ：" + hp);
			System.out.println("攻撃力：" + attackpower);
			System.out.println("防御力：" + defensepower);
			System.out.println("経験値：" + keikenchi);
			System.out.println("＝＝＝＝＝＝＝");
			System.out.println("現在のあなたの位置はこちらです");
		}
	}
	
	// マップクラス
	public static class Map {
		
		// 縦と横
		private int width;
		private int height;
		private char[][] map;
		private Random rand = new Random();
		private Scanner stdIn = new Scanner(System.in);
		private Game game;
		
		// コンストラクタ
		public Map(int width, int height, Game game) {
			this.width = width;
			this.height = height;
			this.map = new char[width][height];
			this.game = game;
			generateMap();
		}
		
		// マップを生成する
		private void generateMap() {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					map[i][j] = '・';
				}
			}
			
			// プレイヤーの位置
			map[0][0] = '★'; 
			
		}
		
		// マップを表示する
		public void display() {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		// プレイヤーが移動する
		public void move(Player player) {
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			System.out.println("移動方向を選んでください！");
			System.out.println("1. ←左");
			System.out.println("2. →右");
			System.out.println("3. ↑上");
			System.out.println("4. ↓下");
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			int choice = game.mukounaInput();
			
			int x = player.getX();
			int y = player.getY();
			
			map[x][y] = '・';
			
			switch (choice) {
				case 1:
					if (y > 0) y--; // 左
					break;
				case 2:
					if (y < height - 1) y++; //右
					break;
				case 3:
					if (x > 0) x--; // 上
					break;
				case 4:
					if (x < width - 1) x++; // 下
					break;
				default:
					System.out.println("無効な選択です！正しい数字を入力してください！");
					break;
			}
			
			player.setX(x);
			player.setY(y);
			
			map[x][y] = '★';
						
			// ５０％の確率でモンスターを出現させる
			if (rand.nextInt(10) < 5) { 
				System.out.println("おっと！");
				System.out.println("モンスターが出現したようだ！");
				Monster monster = new Monster("モンスター");
				Battle battle = new Battle(player, monster);
				game.setCurrentBattle(battle);
				battle.startBattle();
			}
		}
	}
	
	// プレイヤークラス
	public static class Player extends Character {
		
		// 位置
		private int x;
		private int y;
		private boolean defending = false;
		
		// コンストラクタ
		public Player(String name, String gender, String race, String job) {
			super(name, gender, race, job);
			this.x = 0;
			this.y = 0;
		}
		
		// プレイヤーのX座標の取得
		public int getX() {
			return x;
		}
		
		// プレイヤーのX座標の設定
		public void setX(int x) {
			this.x = x;
		}
		
		// プレイヤーのY座標の取得
		public int getY() {
			return y;
		}
		
		// プレイヤーのY座標の設定
		public void setY(int y) {
			this.y = y;
		}
		
		public boolean isDefending() {
			return defending;
		}
		
		public void setDefending(boolean defending) {
			this.defending = defending;
		}
		
		// スキル：ファイアを使う
		public void useFire() {
			System.out.println("ファイアを使いました！");
			this.attackpower = 20;
			System.out.println("次の攻撃からダメージが２０与えられます！");
		}
		
		// スキル：リカバリを使う
		public void useRecover() {
			System.out.println("リカバリを使いました！");
			this.hp += 20;
		
			// HPの上限は100にしておく
			if (this.hp > 100) {
				this.hp = 100;
			}
			System.out.println("ＨＰが２０回復されました！");
			
		}
	}
	
	// モンスタークラス
	public static class Monster {
		
		// モンスターの名前？、HP、攻撃力
		public String name;
		public int hp;
		public int attackpower;
		
		// コンストラクタ
		public Monster(String name) {
			this.name = name;
			this.hp = 100;
			this.attackpower = 10;
		}
		
		// モンスターの攻撃
		public void attack(Player player) {
			int damage = attackpower;
			if (player.isDefending()) {
				
				 // 防御中はダメージを半減させる
				damage /= 2;
				System.out.println(player.name + "のダメージは半減されました！");
			}
			
			System.out.println("【" + name + "】"  + "攻撃しました！");
			player.hp -= damage;
			System.out.println(player.name + "に" + damage + "のダメージを与えました！");
		}
	}
	
	// バトルクラス
	public static class Battle {
		
		// プレイヤー、モンスター
		private Player player;
		private Monster monster;
		
		// コンストラクタ
		public Battle(Player player, Monster monster) {
			this.player = player;
			this.monster = monster;
		}
		
		// バトルを開始する
		public void startBattle() {
			System.out.println("バトルを開始します！");
			System.out.println("あなたが先攻です！頑張って！");
			
			Scanner stdIn = new Scanner(System.in);
			
			while (true) {
				System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
				System.out.println("プレイヤーのＨＰ：" + player.hp);
				System.out.println("モンスターのＨＰ：" + monster.hp);
				System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
				System.out.println("1. 攻撃する");
				System.out.println("2. 防御する");
				System.out.println("3. スキルを使う");
				System.out.println("4. 辞退して逃げる");
				System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
				
				int choice = mukounaInput(stdIn);
				
				switch (choice) {
					case 1:
						attack();
						break;
					case 2:
						player.setDefending(true);
						System.out.println("【プレイヤー】防御しました！");
						break;
					case 3:
						useSkill();
						break;
					case 4:
						endBattle();
						return;
					default:
						System.out.println("無効な選択です！");
						break;
				}
				
				// モンスターのＨＰが０で勝ち、経験値を２０加算する
				if (monster.hp <= 0) {
					System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
					System.out.println("　　　　　おめでとうございます！　　　　　");
					System.out.println("モンスターを倒しました！あなたの勝ちです！");
					System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
					player.addExperience(20); 
					
					// それぞれのHPを初期状態の100にする
					player.hp = 100;
					monster.hp = 100;
					
					return;
				}
				
				monster.attack(player);
				
				// プレイヤーのＨＰが０で負け
				if (player.hp <= 0) {
					System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
					System.out.println("　　プレイヤーのＨＰは０になりました　　");
					System.out.println("　あなたの負けです…次は頑張りましょう　");
					System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
					
					// それぞれのHPを初期状態の100にする
					player.hp = 100;
					monster.hp = 100;
					
					return;
				}
				
				// 攻撃したあとは防御を解除する
				player.setDefending(false); 
			}
		}
		
		// 無効な入力がされたとき
		private int mukounaInput(Scanner stdIn) {
			while (!stdIn.hasNextInt()) {
				System.out.println("無効な選択です！数字を入力してください！");
				stdIn.next();
			}
			return stdIn.nextInt();
		}
		
		// モンスターを攻撃する
		private void attack() {
			System.out.println("【プレイヤー】攻撃しました！");
			monster.hp -= player.attackpower;
			System.out.println("モンスターに" + player.attackpower + "のダメージを与えました！");
		}
		
		// スキルを選択する
		private void useSkill() {
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			System.out.println("スキルを選択してください！");
			System.out.println("1. ファイア（攻撃力がずっと２０）");
			System.out.println("2. リカバリ（ＨＰが２０回復する）");
			System.out.println("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
			int choice = mukounaInput(new Scanner(System.in));
			
			switch (choice) {
				case 1:
					player.useFire();
					break;
				case 2:
					player.useRecover();
					break;
				default:
					System.out.println("無効な選択です！正しい数字を入力してください！");
					break;
			}
		}
		
		// バトルを終了する
		private void endBattle() {
			System.out.println("バトルを終了しました！");
			
			// それぞれのHPを初期状態の100にする
			player.hp = 100;
			monster.hp = 100;
		}
		
	}
	
}

