package com.renanpelicari.minesweeper.business.fixtures;

import com.renanpelicari.minesweeper.domain.model.BoardPosition;

import java.util.HashMap;
import java.util.Map;

public class BoardPositionMapFixtures {

   public static final Map<Integer, BoardPosition> DEFAULT_BOARD_POSITION_MAP = new HashMap<>() {{
      put(CoordinateFixtures.COORDINATE_00.hashCode(), BoardPositionFixtures.BOARD_POSITION_00);
      put(CoordinateFixtures.COORDINATE_01.hashCode(), BoardPositionFixtures.BOARD_POSITION_01);
      put(CoordinateFixtures.COORDINATE_02.hashCode(), BoardPositionFixtures.BOARD_POSITION_02);
      put(CoordinateFixtures.COORDINATE_10.hashCode(), BoardPositionFixtures.BOARD_POSITION_10);
      put(CoordinateFixtures.COORDINATE_11.hashCode(), BoardPositionFixtures.BOARD_POSITION_11);
      put(CoordinateFixtures.COORDINATE_12.hashCode(), BoardPositionFixtures.BOARD_POSITION_12);
      put(CoordinateFixtures.COORDINATE_20.hashCode(), BoardPositionFixtures.BOARD_POSITION_20);
      put(CoordinateFixtures.COORDINATE_21.hashCode(), BoardPositionFixtures.BOARD_POSITION_21);
      put(CoordinateFixtures.COORDINATE_22.hashCode(), BoardPositionFixtures.BOARD_POSITION_22);
   }};

   public static final Map<Integer, BoardPosition> DEFAULT_BOARD_POSITION_MAP_FLAG_CHANGE =
           new HashMap<>(DEFAULT_BOARD_POSITION_MAP) {{
              put(CoordinateFixtures.COORDINATE_22.hashCode(), BoardPositionFixtures.BOARD_POSITION_22_CHANGE_FLAG);
   }};

   public static final Map<Integer, BoardPosition> DEFAULT_BOARD_POSITION_MAP_CLICKING_BOMB =
           new HashMap<>(DEFAULT_BOARD_POSITION_MAP) {{
              put(CoordinateFixtures.COORDINATE_22.hashCode(), BoardPositionFixtures.BOARD_POSITION_22_ALREADY_CLICKED);
   }};
}