package org.opensidewalks.streetcomplete.quests.bus_stop_lit;

import android.os.Bundle;

import org.opensidewalks.streetcomplete.data.osm.SimpleOverpassQuestType;
import org.opensidewalks.streetcomplete.data.osm.changes.StringMapChangesBuilder;
import org.opensidewalks.streetcomplete.data.osm.download.OverpassMapDataDao;
import org.opensidewalks.streetcomplete.quests.AbstractQuestAnswerFragment;

import java.util.Map;

import javax.inject.Inject;

import de.westnordost.streetcomplete.R;

import org.opensidewalks.streetcomplete.quests.YesNoQuestAnswerFragment;

public class AddBusStopLit extends SimpleOverpassQuestType
{
	@Inject public AddBusStopLit(OverpassMapDataDao overpassServer) { super(overpassServer); }

	@Override protected String getTagFilters()
	{
		return "nodes with (public_transport=platform or (highway=bus_stop and public_transport!=stop_position)) and !lit";
	}

	public AbstractQuestAnswerFragment createForm()
	{
		return new YesNoQuestAnswerFragment();
	}

	public void applyAnswerTo(Bundle answer, StringMapChangesBuilder changes)
	{
		String yesno = answer.getBoolean(YesNoQuestAnswerFragment.ANSWER) ? "yes" : "no";
		changes.add("lit", yesno);
	}

	@Override public String getCommitMessage() { return "Add bus stop lit"; }
	@Override public int getIcon() { return R.drawable.ic_quest_bus; }
	@Override public int getTitle(Map<String, String> tags)
	{
		boolean hasName = tags.containsKey("name");
		if(hasName) return R.string.quest_busStopLit_name_title;
		else        return R.string.quest_busStopLit_title;
	}
}