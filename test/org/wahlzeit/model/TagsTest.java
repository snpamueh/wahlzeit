/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.util.ArrayList;

import junit.framework.*;

/**
 * 
 * @author dirkriehle
 * 
 */
public class TagsTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TagsTest.class);
	}

	public TagsTest(String name) {
		super(name);
	}
	
	public void testHasTag() {
		Tags tags1 = new Tags("3k30 asd, fRizT007'a;p , a tag");
		assertTrue(tags1.hasTag("3k30asd"));
		assertFalse(tags1.hasTag("fRizT007ap"));
		assertTrue(tags1.hasTag("frizt007ap"));
		assertTrue(tags1.hasTag("atag"));
	}
	
	public void testGetTagListFromString() {
		ArrayList<String> taglist = Tags.getTagListFromString("wa/HL3z@$.e/1tT", '/');
		assertNotNull(taglist);
		assertTrue(taglist.size() == 3);
		assertFalse(taglist.isEmpty());
		assertTrue(taglist.contains("wa"));
		assertTrue(taglist.contains("hl3ze"));
		assertTrue(taglist.contains("1tt"));
		assertFalse(taglist.contains("HL3z"));
	}
	
	public void testGetSize() {
		Tags tags1 = new Tags("w24a/,HL<>3,6z@$.e/,1tT");
		Tags empty_tags = new Tags("        ");
		assertEquals(4, tags1.getSize());
		assertEquals(0, empty_tags.getSize());
	}
	
	public void testAsTag() {		
		assertEquals(Tags.asTag("flo wer"), "flower");
		assertEquals(Tags.asTag(" 35j lNM#&In>B << f2"), "35jlnminbf2");
		assertEquals(Tags.asTag(",,,,,,"), "");
	}
	
	public void testAsString() {
		Tags tags1 = new Tags("tag1, tag2");
		assertSame(tags1.getSize(), 2);
		assertEquals(tags1.asString(), "tag1, tag2");
		assertEquals(tags1.asString(true, '+'), "tag1 + tag2");		
	}

	public void testTagList() {
		Tags tags1 = new Tags(" flo wer , Kinokuniya, bingo, bongo");
		String[] tags1array = tags1.asArray();
		assertTrue(tags1array.length == 4);
		assertEquals(tags1array[0], "flower");
		assertEquals(tags1array[1], "kinokuniya");
		assertEquals(tags1array[2], "bingo");
		assertEquals(tags1array[3], "bongo");
		assertEquals(tags1.asString(), "flower, kinokuniya, bingo, bongo");
		
		Tags tags2 = new Tags(" @ 2a hum5ug ; yah!, ohmpf ,,,");
		String[] tags2array = tags2.asArray();
		assertEquals(tags2array[0], "2ahum5ugyah");
		assertEquals(tags2array[1], "ohmpf");
	}
}
