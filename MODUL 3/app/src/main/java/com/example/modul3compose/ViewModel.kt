package com.example.modul3compose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ComicViewModel: ViewModel() {
    private val _carouselComics = MutableStateFlow<List<Comic>>(emptyList())
    private val _listComics = MutableStateFlow<List<Comic>>(emptyList())

    val carouselComics = _carouselComics.asStateFlow()
    val listComics = _listComics.asStateFlow()

    init {
        _carouselComics.value = listOf(
            Comic(
                "Please Bully Me Miss Villainess",
                R.drawable.cv_cr_1,
                R.drawable.bg_cr_1,
                "https://mangadex.org/title/8b34f37a-0181-4f0b-8ce3-01217e9a602c/qing-qifu-wo-ba-eyi-xiaojie",
                "I, Yvonne, reincarnated into an otome game as the rich villainess. According to the game's plot, a character loathed by everyone such as myself has the main role of bullying the heroine, pushing her towards the various love interests' romantic routes. But it seems like there's something wrong with Elsa, the heroine! She's getting too close to me!",
                "Romance, Comedy, Transmigration",
                "Chise, Ciweimao (刺猬猫), 断s, Fairy Club (魔仙社)"),
            Comic(
                "Reverend Insanity",
                R.drawable.cv_cr_2,
                R.drawable.bg_cr_2,
                "https://mangadex.org/title/e1809b04-f7dd-4327-9669-ca83884a1403/reverend-insanity-remake",
                "A story of a villain, Fang Yuan, who was reborn 500 years into the past with the Spring Autumn Cicada he painstakingly refined. With his profound wisdom, battle and life experiences, he seeks to overcome his foes with skill and wit! Ruthless and amoral, he has no need to hold back as he pursues his ultimate goals.",
                "Action, Adventure, Wuxia",
                "Gu Zhen Ren, Rococo (Kodoku Studio), LenHC, Z (Kodoku Studio)"),
            Comic(
                "SSS-Class Suicide Hunter",
                R.drawable.cv_cr_3,
                R.drawable.bg_cr_3,
                "https://mangadex.org/title/4a973243-952e-44d7-a50f-883b4b7c9cc2/sss-geup-jugeoya-saneun-hunter",
                "In the mysterious, RPG dungeon-like Tower, Gongja Kim lives a mundane existence, envying all the star hunters. One day, his wish for more is granted with a legendary skill to copy others’ abilities… at the cost of his life. Before he can make sense of it, he’s killed by the #1 hunter, the Flame Emperor! But this activates his skill and now he’s copied a new one, the ability to travel back in time upon death.",
                "Action, Adventure, Time Travel",
                "Neida (네이다), Shin Noah (신노아), Bill K"),
            Comic(
                "I Got a New Skill Every Time I was Exiled, and After 100 Different Worlds, I was Unmatched",
                R.drawable.cv_cr_4,
                R.drawable.bg_cr_4,
                "https://mangadex.org/title/5f64d517-c4ea-40c0-a463-fcc069d39d3b/tsuihou-sareru-tabi-ni-skill-o-te-ni-ireta-ore-ga-100-no-isekai-de-2-shuume-musou",
                "An adventurer named Ed was exiled from his party because he was considered useless, but instead of being sad and upset he was actually happy!!? It turns out that it's not without reason that he feels happy when he's exiled from his party, because every time he's been exiled he will receive a skill that makes him even more invincible...",
                "Reincarnation, Time Travel, Action, Romance",
                "Hinoura Takumi, Nimori Shimatsukasa"),
            Comic(
                "The Greatest Estate Developer",
                R.drawable.cv_cr_5,
                R.drawable.bg_cr_5,
                "https://mangadex.org/title/d7f56ace-cd30-48b9-8b64-afeca0077fca/yeokdaegeum-yeongji-seolgyesa",
                "When civil engineering student Suho Kim falls asleep reading a fantasy novel, he wakes up as a character in the book. Suho is now in the body of Lloyd Frontera, a lazy noble who loves to drink, and whose family is in a mountain of debt. Using his engineering knowledge, Suho designs inventions to avert the terrible future that lies in wait for him.",
                "Comedy, Action, Reincarnation",
                "Lee Hyunmin (이현민), BK_Moon (문백경), Kim Hyeon-Soo (김현수)"),
            Comic(
                "Lord of the Mysteries",
                R.drawable.cv_cr_6,
                R.drawable.bg_cr_6,
                "https://mangadex.org/title/df3f7834-a65b-4d2c-9388-045c9ec03a35/lord-of-mysteries",
                "With the rising tide of steam power and machinery, who can come close to being a Beyonder? Shrouded in the fog of history and darkness, who or what is the lurking evil that murmurs into our ears? Waking up to be faced with a string of mysteries, Zhou Mingrui finds himself reincarnated as Klein Moretti in an alternate Victorian era world where he sees a world filled with machinery, cannons, dreadnoughts, airships, difference machines, as well as Potions, Divination, Hexes, Tarot Cards, Sealed Artifacts…",
                "Transmigration, Historical, Action",
                "Cuttlefish That Loves Diving (爱潜水的乌贼), sevenballoon"),
            Comic(
                "The Summer You Were There",
                R.drawable.cv_cr_7,
                R.drawable.bg_cr_7,
                "https://mangadex.org/title/6ecc62e4-25ad-4102-b0d8-580a8023d2fb/kimi-to-tsuzuru-utakata",
                "Shizuku is a shy high schooler who hardly talks to other people. Instead, she loses herself in writing, crafting a novel that she never intends to show anyone. But when her cute, popular classmate Kaori gets her hands on Shizuku’s manuscript, everything changes",
                "Psychological, Tragedy, Romance",
                "Yuama"),
        )

        _listComics.value = listOf(
            Comic(
                "The Moon on a Rainy Night",
                R.drawable.cv_li_1,
                R.drawable.bg_li_1,
                "https://mangadex.org/title/c8a0426d-539b-4f6a-9537-fbccf4962529/amayo-no-tsuki",
                "One rainy night, Saki is rushing to a piano lesson when she crashes into a beautiful, long-haired girl, dropping her sheet music in the process. Saki stutters an apology, but the girl simply hands back her sheet music and leaves without a word. Saki begins her first day of high school the following morning, only to find the stranger from the night before sitting at the desk next to hers. She learns that the girl's name is Kanon and that she is not quite completely deaf, but very hard of hearing. Though Kanon needs to be close to people to read their lips, she tends to push people away with her icy demeanor. Through one kind gesture, Saki slowly begins breaking down the walls around Kanon, even as she feels something new blossoming within her.",
                "",
                ""),
            Comic(
                "Movies Are Real",
                R.drawable.cv_li_2,
                R.drawable.bg_li_2,
                "https://mangadex.org/title/ff2f35ff-2641-4a1b-8477-df933efd4eba/movies-are-real",
                "While working as a small supporting actor in a movie, Ki Man Sung has nothing else going for him. However, that was when ,through mysterious circumstance, the movie set had become reality during filming. With the sound of the director’s cut, during that short take, he’s not an actor but actually thrown into the events occurring, showing the pain and emotions he faces in that moment. He can only come back if he creates that perfect take…!",
                "",
                ""),
            Comic(
                "Legend of The Northern Blade",
                R.drawable.cv_li_3,
                R.drawable.bg_li_3,
                "https://mangadex.org/title/9ed16bc9-f570-4e71-8dda-aebc098b683b/the-legend-of-the-northern-blade",
                "When the world was plunged into darkness by the ‘Silent Night’, Martial Artists from all over the place gathered to form the ‘Northern Heavenly Sect’. With overwhelming strength from the Northern Heavenly Sect, the Silent Night was pushed away and the people began to enjoy peace once more. However, as time passed the martial artists from the mainlands began to conspire against the ‘Northern Heavenly Sect’, and eventually caused the death of the Fourth Generation Sect Leader, Jin Kwan-Ho, destroying the sect with it. As everyone left the sect, Jin Kwan-Ho’s only son, Jin Mu-Won was left behind. Mu-Won has never learned anything about martial arts, but he eventually finds the Martial Techniques secretly left behind by his father and begins to acquire the martial arts of the Northern Heavenly Sect. Then one day, a mysterious girl appears before Mu-Won…!!",
                "",
                ""),
            Comic(
                "Return of the Mount Hua Sect",
                R.drawable.cv_li_4,
                R.drawable.bg_li_4,
                "https://mangadex.org/title/f0f62b75-5989-4f32-9b59-ab56abe35fc1/return-of-the-blossoming-blade",
                "Chung Myung, The 13th Disciple of the Mount Hua Sect, One of the 3 Great Swordsmen, Plum Blossom Sword Saint, defeated Chun Ma, who has brought destruction and disarray onto the world. After the battle, he breathes his last breath on top of the headquarter mountain of the Heavenly Demon Sect. He is reborn after 100 years in the body of a child. ……What? The Mount Hua Sect has fallen? What kind of nonsense is that!?",
                "",
                ""),
            Comic(
                "The One Within the Villainess",
                R.drawable.cv_li_5,
                R.drawable.bg_li_5,
                "https://mangadex.org/title/6df29d13-2dab-4ca6-a0b5-74070abf3e1d/akuyaku-reijou-no-naka-no-hito",
                "In order to clear the name of 'Emi', a girl who had reincarnated as Remilia, the villainess of an otome game, the real Remilia who had been watching all along inside awakens.",
                "",
                ""),
            Comic(
                "The King's Avatar",
                R.drawable.cv_li_6,
                R.drawable.bg_li_6,
                "https://mangadex.org/title/1930d635-b170-417f-b8a8-f84b881bcc7d/quanzhi-gaoshou",
                "No one could ever come close to Ye Qiu’s skills in Glory, a popular MMO that took the world by storm. However, his professional team replaced him as captain and seized his account. Ye Qiu was left to start anew at an internet café as a network manager. When the new 10th server launched, he dove back in, armed with a decade of experience and an unfinished custom weapon. His path to redemption, filled with strategy and memories, marks his thrilling return to the top.",
                "",
                ""),
            Comic(
                "I Made Friends with the Second Prettiest Girl in My Class",
                R.drawable.cv_li_7,
                R.drawable.bg_li_7,
                "https://mangadex.org/title/8ef11280-30bc-434b-bf61-c61e092905ac/class-de-2-banme-ni-kawaii-onnanoko-to-tomodachi-ni-natta",
                "I, Maki Maehara, am a class loner. My first friend ever is Umi Asanagi. She is always at the center of the social circle, the girl the guys secretly whisper is the \"second cutest in the class.\" I thought a background character like me lived in a completely different world from her, but it turns out she’s a huge fan of B-movies!? After becoming friends through a twist of fate, Asanagi secretly comes to hang out at my house every Friday after school. Movies, games, manga, we have a great time together sharing the exact same hobbies. \"Hey, Maehara, look! I'll let you sit right here as a special treat.\" \"That's my bed to begin with...\" \"Just for today, it's my bed. Come on, get over here.\" Aren't you a little too close, Asanagi?",
                "",
                ""),
        )
    }
}
