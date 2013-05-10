package net.vxinwen.service;

import java.util.List;

import net.vxinwen.bean.Joke;
import net.vxinwen.db.dao.JokeDao;
import net.vxinwen.util.TimestampUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GetJokeService {
    public JSONObject get(long id) {
        // dao query returns Map<category,List<News>>
        // convert to JSON
        JokeDao jokeDao = new JokeDao();
        List<Joke> jokes = jokeDao.getLastNewsBatch(id);
        return convertToJson(jokes);
    }

    private JSONObject convertToJson(List<Joke> jokes) {
        JSONObject result = null;
        JSONArray values = new JSONArray();
        for(Joke joke:jokes){
            JSONObject json = new JSONObject();
            json.put("id", joke.getId());
            json.put("title", joke.getTitle());
            json.put("obdy", joke.getBody());
            json.put("url", joke.getUrl());
            json.put("publishTime", TimestampUtil.timeStampToString(joke.getPublishTime()));
            json.put("image", joke.getImage());
            json.put("source", joke.getSource());
        }
        return null;
    }
}
