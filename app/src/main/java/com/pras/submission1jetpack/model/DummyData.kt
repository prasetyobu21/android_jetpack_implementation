package com.pras.submission1jetpack.model

import com.pras.submission1jetpack.model.local.entity.MovieListEntity
import com.pras.submission1jetpack.model.local.entity.TvListEntity
import com.pras.submission1jetpack.model.remote.response.MovieDetailResponse
import com.pras.submission1jetpack.model.remote.response.MovieListResponse
import com.pras.submission1jetpack.model.remote.response.TvDetailResponse
import com.pras.submission1jetpack.model.remote.response.TvListResponse

object DummyData {
    fun generateMovieList(): List<MovieListEntity> {
        val movieList = ArrayList<MovieListEntity>()

        movieList.add(
            MovieListEntity(
                399566,
                "Godzilla vs. Kong",
                8.1,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )
        return movieList
    }

    fun generateMovieDetail(): MovieListEntity {
        val movie = MovieListEntity(
            399566,
            "Godzilla vs. Kong",
            8.1,
            "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "2021-03-24",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
        )
        return movie
    }

    fun generateTvList(): List<TvListEntity> {
        val tvList = ArrayList<TvListEntity>()

        tvList.add(
            TvListEntity(
                1399,
                "Game of Thrones",
                8.4,
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "2011-04-17",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
            )
        )
        return tvList
    }

    fun generateTvDetail(): TvListEntity {
        val tv = TvListEntity(
            1399,
            "Game of Thrones",
            8.4,
            "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "2011-04-17",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
        )
        return tv
    }

    fun generateMovieResponse(): List<MovieListResponse> {
        val movieListResponse = ArrayList<MovieListResponse>()

        movieListResponse.add(
            MovieListResponse(
                399566,
                "Godzilla vs. Kong",
                8.1,
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
            )
        )
        return movieListResponse
    }

    fun generateMovieDetailResponse(): MovieListResponse {
        val movie = MovieListResponse(
            399566,
            "Godzilla vs. Kong",
            8.1,
            "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            "2021-03-24",
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages."
        )
        return movie
    }

    fun generateTvListResponse(): List<TvListResponse> {
        val tvListResponse = ArrayList<TvListResponse>()

        tvListResponse.add(
            TvListResponse(
                1399,
                "Game of Thrones",
                8.4,
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "2011-04-17",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
            )
        )
        return tvListResponse
    }

    fun generateTvDetailResponse(): TvListResponse {
        val tv = TvListResponse(
            1399,
            "Game of Thrones",
            8.4,
            "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            "2011-04-17",
            "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond."
        )
        return tv
    }
}