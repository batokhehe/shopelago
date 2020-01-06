package com.shopelago.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shopelago.MainActivity;
import com.shopelago.R;
import com.shopelago.view.adapters.MessageInboxAdapter;
import com.shopelago.models.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MessageInboxFragment extends Fragment {

    // TODO: Customize parameter argument names
    // TODO: Customize parameters
    private MainActivity mainActivity;
    private View rootView;
    private Activity activity;
    private Context context;
    private Toolbar toolbar;
    private Toolbar toolbarHome, toolbarDefault;
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Message> listMessage;
    private RecyclerView rvMessage;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private TextView tvInbox, tvSent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MessageInboxFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        context = getContext();
        mainActivity = (MainActivity) getActivity();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_message_inbox, container, false);
        tvInbox = rootView.findViewById(R.id.tvInbox);
        tvSent = rootView.findViewById(R.id.tvSent);
        tvInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reload inbox ?
            }
        });
        tvSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MessageSentFragment();
                FragmentTransaction ft = mainActivity.getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.commit();
            }
        });
        initListMessage();
        return rootView;
    }

    private void initListMessage() {
        listMessage = new ArrayList<>();
        listMessage.add(new Message("Shopelago Info", "http://bhovdair.com/projects/shopelago/profile_picture.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate fermentum elit, quis suscipit dolor convallis vel. Donec libero orci, sollicitudin id augue et, elementum tristique risus.", "5 Apr 2019"));
        listMessage.add(new Message("Shopelago Support", "http://bhovdair.com/projects/shopelago/profile_picture.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate fermentum elit, quis suscipit dolor convallis vel. Donec libero orci, sollicitudin id augue et, elementum tristique risus.", "1 Apr 2019"));
        listMessage.add(new Message("Bill Gates", "http://bhovdair.com/projects/shopelago/profile_picture.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate fermentum elit, quis suscipit dolor convallis vel. Donec libero orci, sollicitudin id augue et, elementum tristique risus.", "16 Feb 2019"));
        listMessage.add(new Message("Jack Ma", "http://bhovdair.com/projects/shopelago/profile_picture.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate fermentum elit, quis suscipit dolor convallis vel. Donec libero orci, sollicitudin id augue et, elementum tristique risus.", "10 Feb 2019"));
        listMessage.add(new Message("Shopelago Info", "http://bhovdair.com/projects/shopelago/profile_picture.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate fermentum elit, quis suscipit dolor convallis vel. Donec libero orci, sollicitudin id augue et, elementum tristique risus.", "11 Jan 2019"));
        listMessage.add(new Message("Shopelago Support", "http://bhovdair.com/projects/shopelago/profile_picture.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce vulputate fermentum elit, quis suscipit dolor convallis vel. Donec libero orci, sollicitudin id augue et, elementum tristique risus.", "5 Jan 2019"));


        rvMessage = rootView.findViewById(R.id.rvMessage);
        rvMessage.setNestedScrollingEnabled(false);
        MessageInboxAdapter adapter = new MessageInboxAdapter(getContext(),listMessage, mListener);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false); ini bisa buat geser ke kanan
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        rvMessage.setLayoutManager(layoutManager);
        rvMessage.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.GONE);
        toolbarDefault.setVisibility(View.VISIBLE);
        TextView tvTitle = toolbarDefault.findViewById(R.id.tvTitle);
        tvTitle.setText(R.string.title_notifications);
    }
    @Override
    public void onStop() {
        super.onStop();
        toolbarHome = mainActivity.findViewById(R.id.toolbar_home);
        toolbarDefault = mainActivity.findViewById(R.id.toolbar_default);
        toolbarHome.setVisibility(View.VISIBLE);
        toolbarDefault.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }
}
