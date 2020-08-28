package id.kostlab.guide;

import java.util.ArrayList;

public class Content {
    public ArrayList<String[]> guideList(){
        ArrayList<String[]> list=new ArrayList<String[]>();
        list.add(new String[]{"Title 1", "Desc 1"});
        list.add(new String[]{"Title 2", "Desc 2"});
        list.add(new String[]{"Title 3", "Desc 3"});
        list.add(new String[]{"Title 4", "Desc 4"});

        return list;
    }

    public ArrayList<String[]> otherList(){
        ArrayList<String[]> list=new ArrayList<String[]>();
        list.add(new String[]{"Title 1", "Desc 1 Other"});
        list.add(new String[]{"Title 2", "Desc 2 Other"});
        list.add(new String[]{"Title 3", "Desc 3 Other"});
        return list;
    }
}
