package com.example.modul4xml.repository

import com.example.modul4xml.R
import com.example.modul4xml.model.Comic

class ComicRepository {
    fun getCarouselComics(): List<Comic> {
        return listOf(
            Comic(
                "Please Bully Me, Miss Villainess!",
                R.drawable.cv_cr_1, R.drawable.bg_cr_1,
                "https://mangadex.org/title/8b34f37a-0181-4f0b-8ce3-01217e9a602c/qing-qifu-wo-ba-eyi-xiaojie",
                listOf(R.string.please_bully_me_miss_villainess),
                listOf(
                    R.string.genre_romance,
                    R.string.genre_comedy,
                    R.string.genre_transmigration
                ),
                "Chise, Ciweimao (刺猬猫), 断s, Fairy Club (魔仙社)", 1
            ),
            Comic(
                "Reverend Insanity",
                R.drawable.cv_cr_2, R.drawable.bg_cr_2,
                "https://mangadex.org/title/e1809b04-f7dd-4327-9669-ca83884a1403/reverend-insanity-remake",
                listOf(R.string.reverend_insanity),
                listOf(R.string.genre_action, R.string.genre_adventure, R.string.genre_wuxia),
                "Gu Zhen Ren, Rococo (Kodoku Studio), LenHC, Z (Kodoku Studio)", 2
            ),
            Comic(
                "SSS-Class Suicide Hunter",
                R.drawable.cv_cr_3, R.drawable.bg_cr_3,
                "https://mangadex.org/title/4a973243-952e-44d7-a50f-883b4b7c9cc2/sss-geup-jugeoya-saneun-hunter",
                listOf(R.string.sss_class_suicide_hunter),
                listOf(R.string.genre_action, R.string.genre_adventure, R.string.genre_time_travel),
                "Neida (네이다), Shin Noah (신노아), Bill K", 3
            ),
            Comic(
                "I Got a New Skill Every Time I Was Exiled",
                R.drawable.cv_cr_4, R.drawable.bg_cr_4,
                "https://mangadex.org/title/5f64d517-c4ea-40c0-a463-fcc069d39d3b/tsuihou-sareru-tabi-ni-skill-o-te-ni-ireta-ore-ga-100-no-isekai-de-2-shuume-musou",
                listOf(R.string.i_got_a_new_skill_every_time_i_was_exiled),
                listOf(
                    R.string.genre_reincarnation,
                    R.string.genre_time_travel,
                    R.string.genre_action,
                    R.string.genre_romance
                ),
                "Hinoura Takumi, Nimori Shimatsukasa", 4
            ),
            Comic(
                "The Greatest Estate Developer",
                R.drawable.cv_cr_5, R.drawable.bg_cr_5,
                "https://mangadex.org/title/d7f56ace-cd30-48b9-8b64-afeca0077fca/yeokdaegeum-yeongji-seolgyesa",
                listOf(R.string.the_greatest_estate_developer),
                listOf(R.string.genre_action, R.string.genre_comedy, R.string.genre_reincarnation),
                "Lee Hyunmin (이현민), BK_Moon (문백경), Kim Hyeon-Soo (김현수)", 5
            ),
            Comic(
                "Lord of the Mysteries",
                R.drawable.cv_cr_6, R.drawable.bg_cr_6,
                "https://mangadex.org/title/df3f7834-a65b-4d2c-9388-045c9ec03a35/lord-of-mysteries",
                listOf(R.string.lord_of_the_mysteries),
                listOf(
                    R.string.genre_transmigration,
                    R.string.genre_historical,
                    R.string.genre_action
                ),
                "Cuttlefish That Loves Diving (爱潜水的乌贼), sevenballoon", 6
            ),
            Comic(
                "The Summer You Were There",
                R.drawable.cv_cr_7, R.drawable.bg_cr_7,
                "https://mangadex.org/title/6ecc62e4-25ad-4102-b0d8-580a8023d2fb/kimi-to-tsuzuru-utakata",
                listOf(R.string.the_summer_you_were_there),
                listOf(
                    R.string.genre_psychological,
                    R.string.genre_tragedy,
                    R.string.genre_romance
                ),
                "Yuama", 7
            )
        )
    }
        fun getListComics(): List<Comic> {
            return listOf(
                Comic(
                    "The Moon on a Rainy Night",
                    R.drawable.cv_li_1, R.drawable.bg_li_1,
                    "https://mangadex.org/title/c8a0426d-539b-4f6a-9537-fbccf4962529/amayo-no-tsuki",
                    listOf(R.string.the_moon_on_a_rainy_night),
                    listOf(R.string.genre_romance, R.string.genre_drama, R.string.genre_school_life),
                    "Kuzushiro", 8
                ),
                Comic(
                    "Movies Are Real",
                    R.drawable.cv_li_2, R.drawable.bg_li_2,
                    "https://mangadex.org/title/ff2f35ff-2641-4a1b-8477-df933efd4eba/movies-are-real",
                    listOf(R.string.movies_are_real),
                    listOf(R.string.genre_action, R.string.genre_psychological, R.string.genre_mystery),
                    "Jaemihalgi (재미핥기), Toon Plus (주)툰플러스, AinJam (아인jam)", 9
                ),
                Comic(
                    "Legend of the Northern Blade",
                    R.drawable.cv_li_3, R.drawable.bg_li_3,
                    "https://mangadex.org/title/9ed16bc9-f570-4e71-8dda-aebc098b683b/the-legend-of-the-northern-blade",
                    listOf(R.string.legend_of_the_northern_blade),
                    listOf(R.string.genre_action, R.string.genre_martial_arts, R.string.genre_adventure),
                    "Ugak (우각), Haemin (해민)", 10
                ),
                Comic(
                    "Return of the Mount Hua Sect",
                    R.drawable.cv_li_4, R.drawable.bg_li_4,
                    "https://mangadex.org/title/f0f62b75-5989-4f32-9b59-ab56abe35fc1/return-of-the-blossoming-blade",
                    listOf(R.string.return_of_the_mount_hua_sect),
                    listOf(R.string.genre_reincarnation, R.string.genre_time_travel, R.string.genre_martial_arts),
                    "Biga (비가), LICO", 11
                ),
                Comic(
                    "The One Within the Villainess",
                    R.drawable.cv_li_5, R.drawable.bg_li_5,
                    "https://mangadex.org/title/6df29d13-2dab-4ca6-a0b5-74070abf3e1d/akuyaku-reijou-no-naka-no-hito",
                    listOf(R.string.the_one_within_the_villainess),
                    listOf(R.string.genre_reincarnation, R.string.genre_action, R.string.genre_psychological),
                    "Makiburo, Shiraume Nazuna", 12
                ),
                Comic(
                    "The King's Avatar",
                    R.drawable.cv_li_6, R.drawable.bg_li_6,
                    "https://mangadex.org/title/1930d635-b170-417f-b8a8-f84b881bcc7d/quanzhi-gaoshou",
                    listOf(R.string.the_kings_avatar),
                    listOf(R.string.genre_action, R.string.genre_comedy, R.string.genre_fantasy),
                    "Blue Butterfly, Yuewen Animation & Comics", 13
                ),
                Comic(
                    "I Made Friends With the Second Prettiest Girl in My Class",
                    R.drawable.cv_li_7, R.drawable.bg_li_7,
                    "https://mangadex.org/title/8ef11280-30bc-434b-bf61-c61e092905ac/class-de-2-banme-ni-kawaii-onnanoko-to-tomodachi-ni-natta",
                    listOf(R.string.i_made_friends_with_the_second_prettiest_girl_in_my_class),
                    listOf(R.string.genre_romance, R.string.genre_comedy, R.string.genre_school_life),
                    "Takata, Ono Rin", 14
                )
            )
    }
}