 
package com.serveroverload.busbooking;

import java.util.ArrayList;

import com.example.busbooking.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final int ASCII_VALUE_OF_A = 64;
	private int NUMBER_OF_ROW = 6;

	private Double totatCost = 0.0;
	private int totalSeats = 0;
	private TextView totalPrice;
	private TextView totalBookedSeats;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// NOTE: setContentView is below, not here

		setContentView(R.layout.bus_layout);

		totalPrice = (TextView) findViewById(R.id.total_cost);
		totalBookedSeats = (TextView) findViewById(R.id.total_seats);

		// Lower Deck
		LinearLayout ud_window_right = (LinearLayout) findViewById(R.id.ud_window_right);
		LinearLayout ud_mid = (LinearLayout) findViewById(R.id.ud_mid);
		LinearLayout ud_window_left = (LinearLayout) findViewById(R.id.ud_window_left);

		// Lower Deck
		LinearLayout ld_window_right = (LinearLayout) findViewById(R.id.ld_window_right);
		LinearLayout ld_mid = (LinearLayout) findViewById(R.id.ld_mid);
		LinearLayout ld_window_left = (LinearLayout) findViewById(R.id.ld_window_left);

		// Set Max Number of Row
		ld_window_right.setWeightSum(NUMBER_OF_ROW);
		ld_mid.setWeightSum(NUMBER_OF_ROW);
		ld_window_left.setWeightSum(NUMBER_OF_ROW);

		ud_window_right.setWeightSum(NUMBER_OF_ROW);
		ud_mid.setWeightSum(NUMBER_OF_ROW);
		ud_window_left.setWeightSum(NUMBER_OF_ROW);

		// Layout Param for Seats
		LayoutParams seatParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		seatParams.weight = 1;
		seatParams.leftMargin = 5;
		seatParams.rightMargin = 5;

		ArrayList<String> upperBirths = new ArrayList<String>();

		for (int i = 0; i < NUMBER_OF_ROW * 3; i++) {
			upperBirths.add("" + i);

		}

		// Add Seats
		for (int rowCount = 1; rowCount <= NUMBER_OF_ROW * 3; rowCount++) {

			// Left Upper
			final Seat ud_LeftWindowSeat = (Seat) LayoutInflater.from(this).inflate(R.layout.seat, null);
			ud_LeftWindowSeat.setLayoutParams(seatParams);
			ud_LeftWindowSeat.setSeatNumber("SL " + rowCount);

			ud_LeftWindowSeat.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateCost(ud_LeftWindowSeat);

					Toast.makeText(getApplicationContext(), "seat # " + ud_LeftWindowSeat.getSeatNumber(), 300).show();

				}
			});

			ud_window_left.addView(ud_LeftWindowSeat);

			// Left Lower
			final Seat ld_LeftWindowSeat = (Seat) LayoutInflater.from(this).inflate(R.layout.seat, null);
			ld_LeftWindowSeat.setLayoutParams(seatParams);
			ld_LeftWindowSeat.setSeatNumber("SL " + ((char) (rowCount + ASCII_VALUE_OF_A)));

			ld_LeftWindowSeat.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateCost(ld_LeftWindowSeat);

					Toast.makeText(getApplicationContext(), "seat # " + ld_LeftWindowSeat.getSeatNumber(), 300).show();

				}
			});
			ld_window_left.addView(ld_LeftWindowSeat);

			// Mid Upper
			final Seat ud_midSeat = (Seat) LayoutInflater.from(this).inflate(R.layout.seat, null);
			ud_midSeat.setLayoutParams(seatParams);
			ud_midSeat.setSeatNumber("SL " + ++rowCount);

			ud_midSeat.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateCost(ud_midSeat);

					Toast.makeText(getApplicationContext(), "seat # " + ud_midSeat.getSeatNumber(), 300).show();

				}
			});
			ud_mid.addView(ud_midSeat);

			// Mid Lower
			final Seat ld_midSeat = (Seat) LayoutInflater.from(this).inflate(R.layout.seat, null);
			ld_midSeat.setLayoutParams(seatParams);
			ld_midSeat.setSeatNumber("SL " + ((char) (rowCount + ASCII_VALUE_OF_A)));

			ld_midSeat.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateCost(ld_midSeat);

					Toast.makeText(getApplicationContext(), "seat # " + ld_midSeat.getSeatNumber(), 300).show();

				}
			});
			ld_mid.addView(ld_midSeat);

			// Right Upper
			final Seat ud_rightWindowSeat = (Seat) LayoutInflater.from(this).inflate(R.layout.seat, null);
			ud_rightWindowSeat.setLayoutParams(seatParams);
			ud_rightWindowSeat.setSeatNumber("SL " + ++rowCount);

			ud_rightWindowSeat.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateCost(ud_rightWindowSeat);

					Toast.makeText(getApplicationContext(), "seat # " + ud_rightWindowSeat.getSeatNumber(), 300).show();

				}
			});
			ud_window_right.addView(ud_rightWindowSeat);

			// Right Lower
			final Seat ld_rightWindowSeat = (Seat) LayoutInflater.from(this).inflate(R.layout.seat, null);
			ld_rightWindowSeat.setLayoutParams(seatParams);
			ld_rightWindowSeat.setSeatNumber("SL " + ((char) (rowCount + ASCII_VALUE_OF_A)));

			ld_rightWindowSeat.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					updateCost(ld_rightWindowSeat);

					Toast.makeText(getApplicationContext(), "seat # " + ld_rightWindowSeat.getSeatNumber(), 300).show();

				}
			});
			ld_window_right.addView(ld_rightWindowSeat);

		}

	}

	private void updateCost(final Seat ud_LeftWindowSeat) {

		if (ud_LeftWindowSeat.setSelected()) {

			totatCost += ud_LeftWindowSeat.getSeatPrice();
			++totalSeats;

		} else {
			totatCost -= ud_LeftWindowSeat.getSeatPrice();
			--totalSeats;
		}

		totalPrice.setText("" + totatCost);
		totalBookedSeats.setText("" + totalSeats);
	}
}
