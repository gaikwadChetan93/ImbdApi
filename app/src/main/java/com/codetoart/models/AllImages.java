package com.codetoart.models;

public class AllImages
{
    private String id;

    private Backdrops[] backdrops;

    private Posters[] posters;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Backdrops[] getBackdrops ()
    {
        return backdrops;
    }

    public void setBackdrops (Backdrops[] backdrops)
    {
        this.backdrops = backdrops;
    }

    public Posters[] getPosters ()
    {
        return posters;
    }

    public void setPosters (Posters[] posters)
    {
        this.posters = posters;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", backdrops = "+backdrops+", posters = "+posters+"]";
    }
}

	