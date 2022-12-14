package br.com.onboarding.animeCamel.domain.service;


import br.com.onboarding.animeCamel.domain.camel.CamelContextWrapper;
import br.com.onboarding.animeCamel.domain.camel.route.AnimeRouter;
import br.com.onboarding.animeCamel.domain.domain.Anime;
import org.apache.camel.ProducerTemplate;

import java.util.List;

//Servic do domínio
public class AnimeService {
    private final ProducerTemplate template;

    public AnimeService(CamelContextWrapper wrapper) {
        this.template = wrapper.createProducerTemplate();
    }
    public List<Anime> findAnime() {
        //List<Anime> animeList = new ArrayList<>();
        //var findAnimeReturn = (List<Anime>) template.requestBody(AnimeRouter.ROUTE_URI, null, Object.class);
        //animeList.addAll(findAnimeReturn);
        return template.requestBody(AnimeRouter.ROUTE_URI, null, List.class);
    }

    public Anime findAnimeById(Long id) {
        return template.requestBody(AnimeRouter.ROUTE_URI_BY_ID, id, Anime.class);
    }

    public Anime saveAnime(Anime anime) {
        return template.requestBody(AnimeRouter.ROUTE_URI_SAVE, anime, Anime.class);
    }

    public Anime updateAnime(Long id, Anime anime) {
        anime.setId(id);
        return template.requestBody(AnimeRouter.ROUTE_URI_UPDATE, anime, Anime.class);
    }

    public void deleteAnime(Long id) {
        template.sendBody(AnimeRouter.ROUTE_URI_DELETE, id);
    }
}