
/*
 * Copyright 1999-2002 Carnegie Mellon University.  
 * Portions Copyright 2002 Sun Microsystems, Inc.  
 * Portions Copyright 2002 Mitsubishi Electronic Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 * 
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL 
 * WARRANTIES.
 *
 */

package edu.cmu.sphinx.decoder.search;
import java.util.Iterator;
import java.util.List;

import edu.cmu.sphinx.util.SphinxProperties;

/**
 * An active list is maintained as a sorted list
 *
 * Note that all scores are represented in LogMath logbase
 */
public interface ActiveList {


    /**
     * Prefix string for all ActiveList properties.
     */
    public final static String PROP_PREFIX
        = "edu.cmu.sphinx.decoder.search.ActiveList.";


    /**
     * property that sets the desired (or target) size for this
     * active list.  This is sometimes referred to as the beam size
     */
    public final static String PROP_ABSOLUTE_BEAM_WIDTH  =
        PROP_PREFIX + "absoluteBeamWidth";

    /**
     * The default value for the PROP_ABSOLUTE_BEAM_WIDTH property
     */
    public final static int PROP_ABSOLUTE_BEAM_WIDTH_DEFAULT = 2000;

    /**
     * Property that sets the minimum score relative to the maximum
     * score in the list for pruning.  Tokens with a score less than
     * relativeBeamWidth * maximumScore will be pruned from the list
     */

    public final static String PROP_RELATIVE_BEAM_WIDTH
        = PROP_PREFIX + "relativeBeamWidth";

    /**
     * The default value for the PROP_RELATIVE_BEAM_WIDTH property
     */
    public final static double PROP_RELATIVE_BEAM_WIDTH_DEFAULT = 0.0;

    /**
     * Property that indicates whether or not the active list will
     * implement 'strict pruning'.  When strict pruning is enabled,
     * the active list will not remove tokens from the active list
     * until they have been completely scored.  If strict pruning is
     * not enabled, tokens can be removed from the active list based
     * upon their entry scores. The default setting is false
     * (disabled).
     */

    public final static String PROP_STRICT_PRUNING
	= PROP_PREFIX + "strictPruning";

    /**
     * The default for the PROP_STRICT_PRUNING property
     */
    public final static boolean PROP_STRICT_PRUNING_DEFAULT = true;


    /**
     * Adds the given token to the list, keeping track of the lowest
     * scoring token
     *
     * @param token the token to add
     */
    public void add(Token token);

    /**
     * Replaces an old token with a new token
     *
     * @param oldToken the token to replace (or null in which case,
     * replace works like add).
     *
     * @param newToken the new token to be placed in the list.
     *
     */
    public void replace(Token oldToken, Token newToken);


    /**
     * Purges the active list of excess members returning a
     * (potentially new) active list
     *
     * @return a purged active list
     */
    public ActiveList purge();

    /**
     * Creates a new version of the active list with
     * the same general properties of this one
     *
     * @return a new active list
     */
    public ActiveList createNew();


    /**
     * Returns the SphinxProperties of this list.
     *
     * @return the SphinxProperties of this list.
     */
    public SphinxProperties getProperties();


    /**
     * Sets the properties for this list
     *
     * @param props the properties for this list
     */
    public void setProperties(SphinxProperties props);


    /**
     * Sets the absolute beam width.
     *
     * @param absoluteBeamWidth the absolute beam width
     */
    public void setAbsoluteBeamWidth(int absoluteBeamWidth);


    /**
     * Sets the relative beam width.
     *
     * @param relativeBeamWidth the linear relative beam width
     */
    public void setRelativeBeamWidth(double relativeBeamWidth);


    /**
     * Returns an iterator over the elements in this active list
     * 
     * @return an iterator
     */
    public Iterator iterator();

    /**
     * Returns the size of this list
     * 
     * @return the size
     */
    public int size();


    /**
     * Gets the list of all tokens
     *
     * @return the set of tokens
     */
    public List getTokens();


    /**
     * gets the beam threshold best upon the best scoring token
     *
     * @return the beam threshold
     */
    public float getBeamThreshold();

    /**
     * gets the best score in the list
     *
     * @return the best score
     */
    public float getBestScore();

    /**
     * Sets the best scoring token for this active list
     *
     * @param token the best scoring token
     */
    public void setBestToken(Token token);

    /**
     * Gets the best scoring token for this active list
     *
     * @return the best scoring token
     */
    public Token getBestToken();
}

