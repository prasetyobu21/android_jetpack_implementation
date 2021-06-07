package com.pras.submission1jetpack.model

import com.pras.submission1jetpack.R

object Data {

    fun generateMovieData(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                R.drawable.poster_a_start_is_born,
                "A Star Is Born (2008)",
                "Drama",
                7.5,
                "2h 16m",
                "Bradley Cooper",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_alita,
                "Alita: Battle Angel (2019)",
                "Action",
                7.2,
                "2h 2m",
                "Robert Rodiguez",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_aquaman,
                "Aquaman (2018)",
                "Action",
                6.9,
                "2h 23m",
                "James Wan",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_bohemian,
                "Bohemian Rhapsody (2018)",
                "Musical",
                8.0,
                "2h 15m",
                "Bryan Singer",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_cold_persuit,
                "Cold Pursuit (2019)",
                "Crime",
                5.7,
                "1h 59m",
                "Hans Petter Moland",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_creed,
                "Creed II (2018)",
                "Drama",
                6.9,
                "2h 10m",
                "Steven Caple Jr.",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_crimes,
                "Fantastic Beast: The Crimes of Grindelwald (2018)",
                "Fantasy",
                6.9,
                "2h 14m",
                "David Yates",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_glass,
                "Glass (2019)",
                "Drama",
                6.7,
                "2h 9m",
                "M. Night Shyamalan",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_how_to_train,
                "How To Train Your Dragon (2010)",
                "Fantasy",
                7.8,
                "1h 38m",
                "Chriss Sanders",
                "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father"
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_infinity_war,
                "Avengers: Infinity War (2019)",
                "Action",
                8.3,
                "2h 29m",
                "Anthony Russo & Joe Russo",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain."
            )
        )

        movie.add(
            MovieEntity(
                R.drawable.poster_marry_queen,
                "Mary Queen of Scots (2018)",
                "Drama",
                6.6,
                "2h 4m",
                "Josie Rourke",
                "In 1561, Mary Stuart, widow of the King of France, returns to Scotland, reclaims her rightful throne and menaces the future of Queen Elizabeth I as ruler of England, because she has a legitimate claim to the English throne. Betrayals, rebellions, conspiracies and their own life choices imperil both Queens. They experience the bitter cost of power, until their tragic fate is finally fulfilled."
            )
        )
        return movie
    }

    fun generateTvShowsData(): List<TVShowEntity>{
        val tvShows = ArrayList<TVShowEntity>()

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_arrow,
                "Arrow (2021)",
                "Action & Adventure",
                6.6,
                8,
                "Greg Berlanti, Marc Guggenheim, Andrew Kreisberg",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_doom_patrol,
                "Doom Patrol (2019)",
                "Sci-Fi & Fantasy",
                7.6,
                2,
                "Jeremy Carver",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_dragon_ball,
                "Dragon Ball (1986)",
                "Animation",
                8.1,
                1,
                "Akira Toriyama",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_fairytail,
                "Fairy Tail (2009)",
                "Animation",
                7.8,
                8,
                "-",
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_family_guy,
                "Family Guy (1999)",
                "Animation",
                7.0,
                19,
                "Seth MacFarlane",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_flash,
                "The Flash (2014)",
                "Sci-Fi",
                7.7,
                7,
                "Greg Berlanti, Geoff Johns, Andrew Kreisberg",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_god,
                "Game of Thrones (2011)",
                "Sci-Fi & Fantasy",
                8.4,
                8,
                "David Benioff, D. B. Weiss",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_gotham,
                "Gotham (2014)",
                "Crime",
                7.5,
                5,
                "Bruno Heller",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?"
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_grey_anatomy,
                "Grey's Anatomy (2005)",
                "Drama",
                8.2,
                17,
                "Shonda Rhimes",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital."
            )
        )

        tvShows.add(
            TVShowEntity(
                R.drawable.poster_hanna,
                "Hanna (2019)",
                "Action & Adventure",
                7.5,
                2,
                "David Farr",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."
            )
        )

        return tvShows
    }
}