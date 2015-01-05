package onyekachi.me.techcabal;

import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * { ArticleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArticleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    // TODO: Rename and change types of parameters
    private int mPosition;
    private Article mArticle;

//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 1.
     * @return A new instance of fragment ArticleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleFragment newInstance(int position) {
        ArticleFragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public ArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_POSITION);
        }
        mArticle = Article.parseCursor(getActivity(), mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_article, container, false);

//        TextView authorAndDateView = (TextView) view.findViewById(R.id.date_author_textview);
//        TextView titleView = (TextView)view.findViewById(R.id.title_textview);
        WebView bodyView = (WebView)view.findViewById(R.id.webview);

//        authorAndDateView.setText("BY " + mArticle.getAuthor() + " / " + mArticle.getDateString());
//        titleView.setText(mArticle.getTitle());

        WebSettings ws = bodyView.getSettings();
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setJavaScriptEnabled(true);
        ws.setUserAgentString("Mozilla/5.0 (Linux; U; Android 2.0; en-us; Droid Build/ESD20) AppleWebKit/530.17 (KHTML, like Gecko) Version/4.0 Mobile Safari/530.17");
        String content = getString(R.string.htmlPrefix) + "BY " + mArticle.getAuthor().toUpperCase() + " / " +
                mArticle.getDateString().toUpperCase() + getString(R.string.titlePrefix) + mArticle.getTitle().toUpperCase() +
                getString(R.string.contentPrefix) + mArticle.getBody() + getString(R.string.htmlSuffix);
        bodyView.loadDataWithBaseURL("file:///android_asset/", content,"text/html", "UTF-8", null);

//        Typeface oswald = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Oswald-Regular.otf");
//        Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
//
//        authorAndDateView.setTypeface(oswald);
//        titleView.setTypeface(oswald);


        return view;
    }

//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p/>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }

}
