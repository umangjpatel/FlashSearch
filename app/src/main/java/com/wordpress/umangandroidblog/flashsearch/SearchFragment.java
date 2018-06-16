package com.wordpress.umangandroidblog.flashsearch;


import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SearchFragment extends Fragment {

    private static final int MIN_WORD_LENGTH = 4;

    private AppCompatEditText mWordEditText;
    private AppCompatTextView mWordFoundTextView;
    private Trie mTrie;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        mWordEditText = view.findViewById(R.id.word_editText);
        mWordFoundTextView = view.findViewById(R.id.word_found_textView);
        setWordFoundText(R.string.word_not_found);
        mTrie = new Trie();
        getWordsFromDictionary();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mWordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Left intentionally
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int stringRes = mTrie.search(s.toString()) ? R.string.word_found :
                        R.string.word_not_found;
                setWordFoundText(stringRes);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //Left intentionally
            }
        });
    }

    private void getWordsFromDictionary() {
        AssetManager assetManager = getActivity().getAssets();
        try {
            InputStream dictionaryReader = assetManager.open("words.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(dictionaryReader));
            String line;
            while ((line = in.readLine()) != null) {
                String word = line.trim();
                if (word.length() >= MIN_WORD_LENGTH)
                    mTrie.insert(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mTrie.insert("dear");
//        mTrie.insert("deal");
//        mTrie.insert("do");
//        mTrie.insert("he");
//        mTrie.insert("hen");
//        mTrie.insert("heat");
    }

    private void setWordFoundText(int stringRes) {
        mWordFoundTextView.setText(stringRes);
    }

}
