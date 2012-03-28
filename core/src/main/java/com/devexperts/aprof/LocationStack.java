/*
 *  Aprof - Java Memory Allocation Profiler
 *  Copyright (C) 2002-2012  Devexperts LLC
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.devexperts.aprof;

/**
* @author Dmitry Paraschenko
*/
final class LocationStack {
    int invocation_point_loc = AProfRegistry.UNKNOWN_LOC;
    int invocation_point_count;
    int invoked_method_loc = AProfRegistry.UNKNOWN_LOC;
    int invoked_method_count = 0;
    int internal_invoked_method_loc = AProfRegistry.UNKNOWN_LOC;
    int internal_invoked_method_count = 0;

    public void addInvocationPoint(int loc) {
        if (invocation_point_count <= 0) {
            invocation_point_count = 0;
            invocation_point_loc = loc;
        }
        invocation_point_count++;
    }

    public void removeInvocationPoint() {
        invocation_point_count--;
        if (invocation_point_count <= 0) {
            invocation_point_count = 0;
            invocation_point_loc = AProfRegistry.UNKNOWN_LOC;
            invoked_method_count = 0;
            invoked_method_loc = AProfRegistry.UNKNOWN_LOC;
        }
    }

    public void addInvokedMethod(int loc) {
        if (invoked_method_count <= 0) {
            invoked_method_count = 0;
            invoked_method_loc = loc;
        }
        invocation_point_count++;
        invoked_method_count++;
    }

    public void removeInvokedMethod() {
        invoked_method_count--;
        invocation_point_count--;
        if (invoked_method_count <= 0) {
            invoked_method_count = 0;
            invoked_method_loc = AProfRegistry.UNKNOWN_LOC;
        }
    }

    public void markInternalInvokedMethod(int loc) {
        if (internal_invoked_method_count <= 0) {
            internal_invoked_method_count = 0;
            internal_invoked_method_loc = loc;
        }
        internal_invoked_method_count++;
    }

    public void unmarkInternalInvokedMethod() {
        internal_invoked_method_count--;
        if (internal_invoked_method_count <= 0) {
            internal_invoked_method_count = 0;
            internal_invoked_method_loc = AProfRegistry.UNKNOWN_LOC;
        }
    }
}