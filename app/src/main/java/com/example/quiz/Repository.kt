package com.example.quiz

class Repository {
    private val questionList: List<Question> = listOf(
        Question("Какой игрок набрал больше всего голов в истории Лиги Чемпионов?",
            "Cristiano Ronaldo",
            "Lionel Messi",
            "Robert Lewandowski",
            "Neymar",
            1),

        Question("В каком году проводилась первая летняя Олимпиада?",
            "1886",
            "1896",
            "1906",
            "1916",
            2),

        Question("Какой город является родиной баскетбола?",
            "Лос-Анджелес",
            "Бостон",
            "Нью-Йорк",
            "Спрингфилд",
            4),

        Question("Какой игрок набрал больше всего очков за один матч в НБА?",
            "Kobe Bryant",
            "Michael Jordan",
            "Wilt Chamberlain",
            "LeBron James",
            3),

        Question("В каком году был основан ФИФА?",
            "1904",
            "1914",
            "1924",
            "1934",
            1),

        Question("Кто является рекордсменом по количеству побед на Гран-При Формулы-1?",
            "Michael Schumacher",
            "Lewis Hamilton",
            "Ayrton Senna",
            "Alain Prost",
            2),

        Question("Какой футбольный клуб является рекордсменом по количеству побед в Лиге Чемпионов?",
            "Real Madrid",
            "Barcelona",
            "Bayern Munich",
            "AC Milan",
            1),

        Question("Какой игрок является рекордсменом по количеству выигранных титулов 'Ролан Гаррос' в одиночном разряде?",
            "Rafael Nadal",
            "Roger Federer",
            "Novak Djokovic",
            "Bjorn Borg",
            1)
        )

    private val wallpapers: List<Wallpaper> = listOf(
        Wallpaper(R.drawable.wallpaper1, 5),
        Wallpaper(R.drawable.wallpaper2, 10),
        Wallpaper(R.drawable.wallpaper3, 15),
        Wallpaper(R.drawable.wallpaper4, 25)
    )

    fun getQuestions(): List<Question>{
        return questionList.shuffled()
    }

    fun getWallpapers(): List<Wallpaper>{
        return wallpapers
    }
}