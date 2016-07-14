package ru.mzherdev.eurocup.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.mzherdev.eurocup.R;
import ru.mzherdev.eurocup.db.model.Match;
import ru.mzherdev.eurocup.tools.AppResources;

/**
 * Created by macuser on 12.07.16.
 */

public class PlayOffEuroAdapter extends RecyclerView.Adapter<PlayOffEuroAdapter.ViewHolder> {

    private List<Match> matches;
    private Context ctx;

    public PlayOffEuroAdapter(Context ctx, List<Match> matches) {
        this.ctx = ctx;
        this.matches = matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public PlayOffEuroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_off_list, parent, false);
        return new PlayOffEuroAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayOffEuroAdapter.ViewHolder viewHolder, final int position) {
        Match match = matches.get(position);
        viewHolder.stageTextView.setText(matches.get(position).getRound());

        String date = String.format("%s/%s/%s", match.getDay(), match.getMonth(), match.getYear());
        viewHolder.dateTextView.setText(date);

        viewHolder.stadiumTextView.setText(match.getStadium());
        viewHolder.homeTeamTextView.setText(match.getHomeTeam());
        viewHolder.awayTeamTextView.setText(match.getAwayTeam());

        String result = String.format("%s : %s", match.getHomeGoalsFullTime(), match.getAwayGoalsFullTime());
        if (match.isAdditionalTime())
            result += " AT";
        viewHolder.resultTextView.setText(result);

        String halfResult = String.format("(%s : %s)", match.getHomeGoalsHalfTime(), match.getAwayGoalsHalfTime());
        viewHolder.halfResultTextView.setText(halfResult);

        if (match.getPenalty() != null && !match.getPenalty().trim().isEmpty())
            viewHolder.penaltyTextView.setText("pen." + match.getPenalty());

        int imageResourceHomeTeamFlag = AppResources.getFlagOfCountry(match.getHomeTeam());
        Picasso.with(ctx)
                .load(imageResourceHomeTeamFlag)
                .resize(40, 28)
                .centerInside()
                .into(viewHolder.homeTeamImageView);
        int imageResourceAwayTeamFlag = AppResources.getFlagOfCountry(match.getAwayTeam());
        Picasso.with(ctx)
                .load(imageResourceAwayTeamFlag)
                .resize(40, 28)
                .centerInside()
                .into(viewHolder.awayTeamImageView);

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView stageTextView;
        TextView dateTextView;
        TextView stadiumTextView;
        TextView homeTeamTextView;
        TextView awayTeamTextView;
        TextView resultTextView;
        TextView halfResultTextView;
        TextView penaltyTextView;

        ImageView homeTeamImageView;
        ImageView awayTeamImageView;


        public ViewHolder(View itemView) {
            super(itemView);
            stageTextView = (TextView) itemView.findViewById(R.id.stage_textView);
            dateTextView = (TextView) itemView.findViewById(R.id.date_textView);
            stadiumTextView = (TextView) itemView.findViewById(R.id.stadium_textView);
            homeTeamTextView = (TextView) itemView.findViewById(R.id.home_team_textView);
            awayTeamTextView = (TextView) itemView.findViewById(R.id.away_team_textView);
            resultTextView = (TextView) itemView.findViewById(R.id.result_textView);
            halfResultTextView = (TextView) itemView.findViewById(R.id.half_result_textView);
            penaltyTextView = (TextView) itemView.findViewById(R.id.penalty_textView);

            homeTeamImageView = (ImageView) itemView.findViewById(R.id.home_team_imageView);
            awayTeamImageView = (ImageView) itemView.findViewById(R.id.away_team_imageView);
        }
    }
}
