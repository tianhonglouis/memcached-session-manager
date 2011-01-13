/*
 * Copyright 2011 Martin Grotzke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an &quot;AS IS&quot; BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.javakaffee.web.msm;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Nonnull;

import net.spy.memcached.MemcachedClient;
import de.javakaffee.web.msm.MemcachedBackupSessionManager.LockStatus;
import de.javakaffee.web.msm.SessionTrackerValve.SessionBackupService.BackupResultStatus;

/**
 * Represents the session locking hooks that must be implemented by the various
 * locking strategies.
 *
 * @author <a href="mailto:martin.grotzke@javakaffee.de">Martin Grotzke</a>
 */
public class LockingStrategyAll extends LockingStrategy {

    public LockingStrategyAll( @Nonnull final MemcachedClient memcached ) {
        super( memcached );
    }

    @Override
    protected void detectSessionReadOnlyRequestPattern( final Future<BackupResultStatus> result, final String requestId ) {
        // Nothing to do
    }

    @Override
    protected LockStatus lockBeforeLoadingFromMemcached( @Nonnull final String sessionId ) throws InterruptedException, ExecutionException {
        return lock( sessionId );
    }

    @Override
    protected boolean isContainerSessionLookup() {
        return false;
    }

}