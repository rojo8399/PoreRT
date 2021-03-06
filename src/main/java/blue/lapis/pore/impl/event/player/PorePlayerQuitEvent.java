/*
 * Pore(RT)
 * Copyright (c) 2014-2016, Lapis <https://github.com/LapisBlue>
 * Copyright (c) 2014-2016, Contributors
 *
 * The MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package blue.lapis.pore.impl.event.player;

import static com.google.common.base.Preconditions.checkNotNull;

import blue.lapis.pore.event.PoreEvent;
import blue.lapis.pore.event.RegisterEvent;
import blue.lapis.pore.impl.entity.PorePlayer;
import blue.lapis.pore.util.PoreText;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;

@RegisterEvent
public final class PorePlayerQuitEvent extends PlayerQuitEvent implements PoreEvent<ClientConnectionEvent.Disconnect> {

    private final ClientConnectionEvent.Disconnect handle;

    public PorePlayerQuitEvent(ClientConnectionEvent.Disconnect handle) {
        super(null, null);
        this.handle = checkNotNull(handle, "handle");
    }

    @Override
    public ClientConnectionEvent.Disconnect getHandle() {
        return handle;
    }

    @Override
    public Player getPlayer() {
        return PorePlayer.of(handle.getTargetEntity());
    }

    @Override
    public String getQuitMessage() {
        return getHandle().isMessageCancelled() ? null : PoreText.convert(getHandle().getMessage());
    }

    @Override
    public void setQuitMessage(String quitMessage) {
        if (quitMessage == null) {
            this.handle.setMessageCancelled(true);
        } else {
            this.handle.setMessage(PoreText.convert(quitMessage));
        }
    }

    @Override
    public String toString() {
        return toStringHelper().toString();
    }

}
