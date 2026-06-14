<?php

namespace Core\Domain\Shared\Event;

trait Eventable
{
    private array $events = [];

    public function recordEvent(object $event): void
    {
        $this->events[] = $event;
    }

    /**
     * @return object[] events
     */
    public function releaseEvents(): array
    {
        $events = $this->events;
        $this->events = [];
        return $events;
    }
}
