package com.serveroverload.busbooking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.busbooking.R;

public class BusLayoutActivity extends Activity {

	// Test Data Inputs
	private final int NUMBER_OF_SEATS_LEFT_UPPER = 5;
	private final int NUMBER_OF_SEATS_RIGHT_UPPER = 5;
	private final int NUMBER_OF_ROWS_LEFT_UPPER = 1;
	private final int NUMBER_OF_ROWS_RIGHT_UPPER = 2;

	private final int NUMBER_OF_SEATS_LEFT_LOWER = 8;
	private final int NUMBER_OF_SEATS_RIGHT_LOWER = 8;
	
	private final int NUMBER_OF_ROWS_LEFT_LOWER = 3;
	private final int NUMBER_OF_ROWS_RIGHT_LOWER = 1;

	private Double totatCost = 0.0;
	private int totalSeats = 0;
	private TextView totalPrice;
	private TextView totalBookedSeats;

	private LayoutParams seatParams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bus_layout);

		// Fetch Required Layouts
		RelativeLayout busLayoutUpper = (RelativeLayout) findViewById(R.id.upper_deck);
		RelativeLayout busLayoutBottom = (RelativeLayout) findViewById(R.id.lower_deck);
		totalPrice = (TextView) findViewById(R.id.total_cost);
		totalBookedSeats = (TextView) findViewById(R.id.total_seats);

		// Layout Param for Seats
		seatParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT);
		seatParams.weight = 1;
		seatParams.leftMargin = 5;
		seatParams.rightMargin = 5;

		// Add Bottom Seats
		addLeftSeats(NUMBER_OF_ROWS_LEFT_UPPER, NUMBER_OF_SEATS_LEFT_UPPER,
				busLayoutBottom);
		addRightSeats(NUMBER_OF_ROWS_RIGHT_UPPER, NUMBER_OF_SEATS_RIGHT_UPPER,
				busLayoutBottom);

		// Add Upper Seats
		addLeftSeats(NUMBER_OF_ROWS_LEFT_LOWER, NUMBER_OF_SEATS_LEFT_LOWER,
				busLayoutUpper);
		addRightSeats(NUMBER_OF_ROWS_RIGHT_LOWER, NUMBER_OF_SEATS_RIGHT_LOWER,
				busLayoutUpper);

	}

	/*
	 * This function draw seats for right row
	 */
	private void addRightSeats(int numberOfRowsRight, float numberOfSeatsInRow,
			ViewGroup busLayout) {
		int previousRow;

		// ADD RIGHT ROWS

		previousRow = 0;

		// Begin adding rows
		for (int rightRowCount = 0; rightRowCount < numberOfRowsRight; rightRowCount++) {

			// Adding Linear layout as row
			LinearLayout rightRow = new LinearLayout(getApplicationContext());
			rightRow.setGravity(Gravity.CENTER);
			rightRow.setId(100 + rightRowCount);

			// make seats equal distance
			rightRow.setWeightSum(numberOfSeatsInRow);

			// if it is first row add row to window (ALIGN_PARENT_BOTTOM)
			// else add row above window row (ABOVE)
			if (previousRow != rightRowCount) {
				RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				newParams.addRule(RelativeLayout.ABOVE, 100 + previousRow);
				newParams.setMargins(10, 10, 10, 10);
				rightRow.setLayoutParams(newParams);

			} else {

				// Layout params for first row (Window seat), adding margin and
				// bottom alignment
				RelativeLayout.LayoutParams rightRowParam = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				rightRowParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				rightRowParam.setMargins(10, 10, 10, 10);
				rightRow.setLayoutParams(rightRowParam);
			}

			// Add Seats in row we have added
			for (int rowCount = 0; rowCount <= numberOfSeatsInRow; rowCount++) {

				// Left Upper
				final Seat rightRowSeat = (Seat) LayoutInflater.from(this)
						.inflate(R.layout.seat, null);
				rightRowSeat.setLayoutParams(seatParams);
				rightRowSeat.setSeatNumber("SL" + rowCount);
				rightRowSeat.setGravity(Gravity.CENTER);

				rightRowSeat.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						updateCost(rightRowSeat);

						Toast.makeText(getApplicationContext(),
								"seat # " + rightRowSeat.getSeatNumber(), 300)
								.show();

					}
				});

				// add seat to row
				rightRow.addView(rightRowSeat);

			}

			// and then add row to bus layout
			busLayout.addView(rightRow);

			// update row counter
			previousRow = rightRowCount;

		}
	}

	/*
	 * This function draw seats for left row
	 */
	private void addLeftSeats(int numberOfRowsLeft, float nuumberOfSeatsInRow,
			ViewGroup busLayout) {
		// Adding Left side rows

		int previousRow = 0;

		for (int leftRowCount = 0; leftRowCount < numberOfRowsLeft; leftRowCount++) {

			// Adding Linear layout as row
			LinearLayout LeftRow = new LinearLayout(getApplicationContext());
			LeftRow.setGravity(Gravity.CENTER);
			LeftRow.setId(leftRowCount);

			// Equi distance seats
			LeftRow.setWeightSum(nuumberOfSeatsInRow);

			// if it is first row add row to window
			// else add row below window row (BELOW)
			if (previousRow != leftRowCount) {
				RelativeLayout.LayoutParams newParams = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				newParams.addRule(RelativeLayout.BELOW, previousRow);
				newParams.setMargins(10, 10, 10, 10);
				LeftRow.setLayoutParams(newParams);

			} else {
				RelativeLayout.LayoutParams leftRowParam = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				leftRowParam.setMargins(10, 10, 10, 10);
				LeftRow.setLayoutParams(leftRowParam);
			}

			// Add Seats in row
			for (int rowCount = 0; rowCount <= nuumberOfSeatsInRow; rowCount++) {

				// Left Upper
				final Seat leftRowSeat = (Seat) LayoutInflater.from(this)
						.inflate(R.layout.seat, null);
				leftRowSeat.setLayoutParams(seatParams);
				leftRowSeat.setSeatNumber("SL" + rowCount);
				leftRowSeat.setGravity(Gravity.CENTER);

				leftRowSeat.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						updateCost(leftRowSeat);

						Toast.makeText(getApplicationContext(),
								"seat # " + leftRowSeat.getSeatNumber(), 300)
								.show();
					}
				});

				LeftRow.addView(leftRowSeat);

			}

			// add row to bus layout
			busLayout.addView(LeftRow);

			// update row counter
			previousRow = leftRowCount;

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